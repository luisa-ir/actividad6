package com.example.actividad6.ejercicios.ejercicio14

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class VistaDibujoMultitactil @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val coloresDedo = listOf(
        Color.RED,
        Color.BLUE,
        Color.GREEN,
        Color.MAGENTA,
        Color.CYAN,
        Color.parseColor("#FF9800") // Naranja
    )

    private val trazosCompletados = mutableListOf<Pair<Path, Paint>>()
    private val rutasActivas = HashMap<Int, Pair<Path, Paint>>()

    private fun crearPaintParaDedo(pointerId: Int): Paint {
        val color = coloresDedo[pointerId % coloresDedo.size]
        return Paint().apply {
            this.color = color
            isAntiAlias = true
            strokeWidth = 10f
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val actionIndex = event.actionIndex
        val pointerId = event.getPointerId(actionIndex)

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                val x = event.getX(actionIndex)
                val y = event.getY(actionIndex)
                val path = Path().apply { moveTo(x, y) }
                val paint = crearPaintParaDedo(pointerId)
                rutasActivas[pointerId] = Pair(path, paint)
                invalidate()
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                for (i in 0 until event.pointerCount) {
                    val id = event.getPointerId(i)
                    val x = event.getX(i)
                    val y = event.getY(i)
                    rutasActivas[id]?.first?.lineTo(x, y)
                }
                invalidate()
                return true
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP, MotionEvent.ACTION_CANCEL -> {
                rutasActivas.remove(pointerId)?.let {
                    trazosCompletados.add(it)
                }
                invalidate()
                performClick()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (par in trazosCompletados) {
            canvas.drawPath(par.first, par.second)
        }

        for (par in rutasActivas.values) {
            canvas.drawPath(par.first, par.second)
        }
    }

    fun limpiarLienzo() {
        trazosCompletados.clear()
        rutasActivas.clear()
        invalidate()
    }
}
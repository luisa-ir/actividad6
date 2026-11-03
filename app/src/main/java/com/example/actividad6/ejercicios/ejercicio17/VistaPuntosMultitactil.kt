package com.example.actividad6.ejercicios.ejercicio17

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class VistaPuntosMultitactil @JvmOverloads constructor(
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
        Color.parseColor("#FF9800")
    )

    private val puntosActivos = HashMap<Int, PointF>()

    private val paintCircle = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }

    private val paintText = Paint().apply {
        color = Color.WHITE
        isAntiAlias = true
        textSize = 36f
        textAlign = Paint.Align.CENTER
        isFakeBoldText = true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val actionIndex = event.actionIndex
        val pointerId = event.getPointerId(actionIndex)

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                puntosActivos[pointerId] = PointF(event.getX(actionIndex), event.getY(actionIndex))
                invalidate()
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                for (i in 0 until event.pointerCount) {
                    val id = event.getPointerId(i)
                    puntosActivos[id] = PointF(event.getX(i), event.getY(i))
                }
                invalidate()
                return true
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP, MotionEvent.ACTION_CANCEL -> {
                puntosActivos.remove(pointerId)
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

        for ((id, point) in puntosActivos) {
            paintCircle.color = coloresDedo[id % coloresDedo.size]
            canvas.drawCircle(point.x, point.y, 70f, paintCircle)
            canvas.drawText("${id + 1}", point.x, point.y + 12f, paintText)
        }
    }
}
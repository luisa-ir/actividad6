package com.example.actividad6.ejercicios.ejercicio11

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class VistaDibujo @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val trazosGuardados = mutableListOf<Path>()
    private var pathActual: Path? = null

    private val paint = Paint().apply {
        color = Color.BLUE
        isAntiAlias = true
        strokeWidth = 8f
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                pathActual = Path().apply {
                    moveTo(x, y)
                }
                invalidate()
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                pathActual?.lineTo(x, y)
                invalidate()
                return true
            }
            MotionEvent.ACTION_UP -> {
                pathActual?.let {
                    it.lineTo(x, y)
                    trazosGuardados.add(it)
                }
                pathActual = null
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

        // Dibujar todos los trazos almacenados previamente
        for (path in trazosGuardados) {
            canvas.drawPath(path, paint)
        }

        // Dibujar el trazo actual en movimiento
        pathActual?.let {
            canvas.drawPath(it, paint)
        }
    }

    fun limpiarLienzo() {
        trazosGuardados.clear()
        pathActual = null
        invalidate()
    }
}
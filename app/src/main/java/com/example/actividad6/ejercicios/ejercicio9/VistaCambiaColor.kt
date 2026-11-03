package com.example.actividad6.ejercicios.ejercicio9

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.random.Random

class VistaCambiaColor @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val colores = listOf(
        Color.parseColor("#FFCDD2"), // Rojo claro
        Color.parseColor("#C8E6C9"), // Verde claro
        Color.parseColor("#BBDEFB"), // Azul claro
        Color.parseColor("#FFF9C4"), // Amarillo claro
        Color.parseColor("#E1BEE7"), // Púrpura claro
        Color.parseColor("#FFE0B2"), // Naranja claro
        Color.parseColor("#B2EBF2")  // Cyan claro
    )

    private var indiceColor = 0

    init {
        setBackgroundColor(colores[indiceColor])
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            cambiarColor()
            performClick()
            return true
        }
        return super.onTouchEvent(event)
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }

    private fun cambiarColor() {
        var nuevoIndice: Int
        do {
            nuevoIndice = Random.nextInt(colores.size)
        } while (nuevoIndice == indiceColor)
        
        indiceColor = nuevoIndice
        setBackgroundColor(colores[indiceColor])
    }
}
package com.example.actividad6.ejercicios.ejercicio8

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.actividad6.R

class Ejercicio8Activity : AppCompatActivity() {

    private lateinit var tvCoordenadas: TextView
    private lateinit var touchArea: View

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio8)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 8"

        tvCoordenadas = findViewById(R.id.tvCoordenadas)
        touchArea = findViewById(R.id.touchArea)

        touchArea.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                    val x = event.x.toInt()
                    val y = event.y.toInt()
                    tvCoordenadas.text = "X: $x px | Y: $y px"
                    true
                }
                MotionEvent.ACTION_UP -> {
                    view.performClick()
                    true
                }
                else -> false
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
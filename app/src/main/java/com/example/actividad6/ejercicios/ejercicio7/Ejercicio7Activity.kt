package com.example.actividad6.ejercicios.ejercicio7

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.actividad6.R

class Ejercicio7Activity : AppCompatActivity() {

    private lateinit var lblEstadoTouch: TextView

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio7)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 7"

        lblEstadoTouch = findViewById(R.id.lblEstadoTouch)

        lblEstadoTouch.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    lblEstadoTouch.text = "Pulsación iniciada"
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    lblEstadoTouch.text = "Desplazando el dedo"
                    true
                }
                MotionEvent.ACTION_UP -> {
                    view.performClick()
                    lblEstadoTouch.text = "Pulsación finalizada"
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
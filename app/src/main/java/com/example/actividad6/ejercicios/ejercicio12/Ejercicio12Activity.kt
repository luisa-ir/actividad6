package com.example.actividad6.ejercicios.ejercicio12

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.actividad6.R

class Ejercicio12Activity : AppCompatActivity() {

    private lateinit var txtGestoProcesado: TextView
    private lateinit var superficieGesto: View
    private lateinit var detectorGestos: GestureDetector

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio12)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 12"

        txtGestoProcesado = findViewById(R.id.txtGestoProcesado)
        superficieGesto = findViewById(R.id.superficieGesto)

        detectorGestos = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                txtGestoProcesado.text = "Gesto Detectado -> Toque Simple (Tap)"
                return true
            }

            override fun onLongPress(e: MotionEvent) {
                txtGestoProcesado.text = "Gesto Detectado -> Presión Larga (Long Press)"
            }

            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                txtGestoProcesado.text = "Gesto Detectado -> Deslizamiento Rápido (Fling)"
                return true
            }

            override fun onDown(e: MotionEvent): Boolean {
                return true
            }
        })

        superficieGesto.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                v.performClick()
            }
            detectorGestos.onTouchEvent(event)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
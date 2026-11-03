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

    private lateinit var tvGestoDetectado: TextView
    private lateinit var gestureArea: View
    private lateinit var gestureDetector: GestureDetector

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio12)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 12"

        tvGestoDetectado = findViewById(R.id.tvGestoDetectado)
        gestureArea = findViewById(R.id.gestureArea)

        gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                tvGestoDetectado.text = "Gesto: Toque simple (Tap)"
                return true
            }

            override fun onLongPress(e: MotionEvent) {
                tvGestoDetectado.text = "Gesto: Toque prolongado (Long Press)"
            }

            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                tvGestoDetectado.text = "Gesto: Deslizamiento rápido (Fling)"
                return true
            }

            override fun onDown(e: MotionEvent): Boolean {
                return true
            }
        })

        gestureArea.setOnTouchListener { view, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                view.performClick()
            }
            gestureDetector.onTouchEvent(event)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
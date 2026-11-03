package com.example.actividad6.ejercicios.ejercicio13

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.actividad6.R

class Ejercicio13Activity : AppCompatActivity() {

    private lateinit var tvCantidadDedos: TextView
    private lateinit var tvPosicionesDedos: TextView
    private lateinit var touchArea: View

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio13)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 13"

        tvCantidadDedos = findViewById(R.id.tvCantidadDedos)
        tvPosicionesDedos = findViewById(R.id.tvPosicionesDedos)
        touchArea = findViewById(R.id.touchArea)

        touchArea.setOnTouchListener { view, event ->
            val action = event.actionMasked
            val count = if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) 0 else event.pointerCount

            tvCantidadDedos.text = "Dedos en pantalla: $count"

            if (count == 0) {
                tvPosicionesDedos.text = "Toca la pantalla con uno o varios dedos..."
            } else {
                val sb = StringBuilder()
                for (i in 0 until count) {
                    val id = event.getPointerId(i)
                    val x = event.getX(i).toInt()
                    val y = event.getY(i).toInt()
                    sb.append("Dedo ${id + 1} -> X: $x px, Y: $y px\n")
                }
                tvPosicionesDedos.text = sb.toString().trim()
            }

            if (action == MotionEvent.ACTION_UP) {
                view.performClick()
            }
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
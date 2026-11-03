package com.example.actividad6.ejercicios.ejercicio16

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.actividad6.R
import kotlin.math.atan2

class Ejercicio16Activity : AppCompatActivity() {

    private lateinit var tvAngulo: TextView
    private lateinit var rotationArea: View
    private lateinit var ivRotateTarget: ImageView

    private var baseAngle = 0f

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio16)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 16"

        tvAngulo = findViewById(R.id.tvAngulo)
        rotationArea = findViewById(R.id.rotationArea)
        ivRotateTarget = findViewById(R.id.ivRotateTarget)

        rotationArea.setOnTouchListener { view, event ->
            if (event.pointerCount >= 2) {
                val x1 = event.getX(0)
                val y1 = event.getY(0)
                val x2 = event.getX(1)
                val y2 = event.getY(1)

                val angle = Math.toDegrees(atan2((y2 - y1).toDouble(), (x2 - x1).toDouble())).toFloat()

                when (event.actionMasked) {
                    MotionEvent.ACTION_POINTER_DOWN -> {
                        baseAngle = angle - ivRotateTarget.rotation
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val currentRotation = angle - baseAngle
                        ivRotateTarget.rotation = currentRotation
                        val anguloNormalizado = (currentRotation % 360 + 360) % 360
                        tvAngulo.text = String.format("Ángulo de rotación: %.1f°", anguloNormalizado)
                    }
                }
            }

            if (event.action == MotionEvent.ACTION_UP) {
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
package com.example.actividad6.ejercicios.ejercicio18

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.actividad6.R
import kotlin.math.atan2

class Ejercicio18Activity : AppCompatActivity() {

    private lateinit var ivControlTarget: ImageView
    private lateinit var scaleGestureDetector: ScaleGestureDetector

    private var scaleFactor = 1.0f
    private var baseAngle = 0f
    private var dX = 0f
    private var dY = 0f

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio18)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 18"

        ivControlTarget = findViewById(R.id.ivControlTarget)

        scaleGestureDetector = ScaleGestureDetector(this, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                scaleFactor *= detector.scaleFactor
                scaleFactor = scaleFactor.coerceIn(0.4f, 4.0f)
                ivControlTarget.scaleX = scaleFactor
                ivControlTarget.scaleY = scaleFactor
                return true
            }
        })

        findViewById<android.view.View>(R.id.transformContainer).setOnTouchListener { view, event ->
            scaleGestureDetector.onTouchEvent(event)

            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    dX = ivControlTarget.x - event.rawX
                    dY = ivControlTarget.y - event.rawY
                }
                MotionEvent.ACTION_POINTER_DOWN -> {
                    if (event.pointerCount >= 2) {
                        val x1 = event.getX(0)
                        val y1 = event.getY(0)
                        val x2 = event.getX(1)
                        val y2 = event.getY(1)
                        val angle = Math.toDegrees(atan2((y2 - y1).toDouble(), (x2 - x1).toDouble())).toFloat()
                        baseAngle = angle - ivControlTarget.rotation
                    }
                }
                MotionEvent.ACTION_MOVE -> {
                    if (event.pointerCount == 1) {
                        // Mover (Arrastre)
                        ivControlTarget.x = event.rawX + dX
                        ivControlTarget.y = event.rawY + dY
                    } else if (event.pointerCount >= 2) {
                        // Rotar (Girar)
                        val x1 = event.getX(0)
                        val y1 = event.getY(0)
                        val x2 = event.getX(1)
                        val y2 = event.getY(1)
                        val angle = Math.toDegrees(atan2((y2 - y1).toDouble(), (x2 - x1).toDouble())).toFloat()
                        ivControlTarget.rotation = angle - baseAngle
                    }
                }
                MotionEvent.ACTION_UP -> {
                    view.performClick()
                }
            }
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
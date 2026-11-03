package com.example.actividad6.ejercicios.ejercicio15

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.actividad6.R

class Ejercicio15Activity : AppCompatActivity() {

    private lateinit var ivZoomImage: ImageView
    private lateinit var zoomContainer: View
    private lateinit var scaleGestureDetector: ScaleGestureDetector

    private var scaleFactor = 1.0f

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio15)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 15"

        ivZoomImage = findViewById(R.id.ivZoomImage)
        zoomContainer = findViewById(R.id.zoomContainer)

        scaleGestureDetector = ScaleGestureDetector(this, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                scaleFactor *= detector.scaleFactor
                scaleFactor = scaleFactor.coerceIn(0.5f, 4.0f)

                ivZoomImage.scaleX = scaleFactor
                ivZoomImage.scaleY = scaleFactor
                return true
            }
        })

        zoomContainer.setOnTouchListener { view, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                view.performClick()
            }
            scaleGestureDetector.onTouchEvent(event)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
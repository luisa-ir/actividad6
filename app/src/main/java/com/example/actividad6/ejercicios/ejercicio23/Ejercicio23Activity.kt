package com.example.actividad6.ejercicios.ejercicio23

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.actividad6.R

class Ejercicio23Activity : AppCompatActivity(), SensorEventListener {

    private lateinit var containerInclinacion: View
    private lateinit var ivCirculoInclinado: ImageView

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null

    private var posX = 0f
    private var posY = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio23)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 23"

        containerInclinacion = findViewById(R.id.containerInclinacion)
        ivCirculoInclinado = findViewById(R.id.ivCirculoInclinado)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onResume() {
        super.onResume()
        accelerometer?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_GAME)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val ax = event.values[0]
            val ay = event.values[1]

            val containerWidth = containerInclinacion.width.toFloat()
            val containerHeight = containerInclinacion.height.toFloat()
            val ballWidth = ivCirculoInclinado.width.toFloat()
            val ballHeight = ivCirculoInclinado.height.toFloat()

            if (containerWidth > 0 && containerHeight > 0) {
                posX -= ax * 3f
                posY += ay * 3f

                val maxX = (containerWidth - ballWidth).coerceAtLeast(0f)
                val maxY = (containerHeight - ballHeight).coerceAtLeast(0f)

                posX = posX.coerceIn(0f, maxX)
                posY = posY.coerceIn(0f, maxY)

                ivCirculoInclinado.x = posX
                ivCirculoInclinado.y = posY
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
package com.example.actividad6.ejercicios.ejercicio27

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.actividad6.R
import java.util.Locale
import kotlin.math.abs

class Ejercicio27Activity : AppCompatActivity(), SensorEventListener {

    private lateinit var tvPosicionDispositivo: TextView
    private lateinit var tvValoresAcelerometro: TextView

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio27)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 27"

        tvPosicionDispositivo = findViewById(R.id.tvPosicionDispositivo)
        tvValoresAcelerometro = findViewById(R.id.tvValoresAcelerometro)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onResume() {
        super.onResume()
        accelerometer?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            tvValoresAcelerometro.text = String.format(Locale.getDefault(), "X: %.1f | Y: %.1f | Z: %.1f", x, y, z)

            val posicionText = when {
                z > 7.0f -> "Boca Arriba"
                z < -7.0f -> "Boca Abajo"
                abs(y) > 6.0f -> "Posición Vertical"
                else -> "Inclinado"
            }

            tvPosicionDispositivo.text = "Posición:\n$posicionText"
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
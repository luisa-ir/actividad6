package com.example.actividad6.ejercicios.ejercicio19

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.actividad6.R

class Ejercicio19Activity : AppCompatActivity(), SensorEventListener {

    private lateinit var layoutFondo: View
    private lateinit var tvEstadoProximidad: TextView

    private lateinit var sensorManager: SensorManager
    private var proximitySensor: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio19)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 19"

        layoutFondo = findViewById(R.id.layoutFondo)
        tvEstadoProximidad = findViewById(R.id.tvEstadoProximidad)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        if (proximitySensor == null) {
            tvEstadoProximidad.text = "Este dispositivo no cuenta con sensor de proximidad."
        }
    }

    override fun onResume() {
        super.onResume()
        proximitySensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_PROXIMITY) {
            val distancia = event.values[0]
            val maxRange = proximitySensor?.maximumRange ?: 5f

            if (distancia < maxRange && distancia < 5.0f) {
                // Cerca -> Fondo rojo
                layoutFondo.setBackgroundColor(Color.parseColor("#FFCDD2"))
                tvEstadoProximidad.text = "Objeto Cerca\n($distancia cm)"
                tvEstadoProximidad.setTextColor(Color.parseColor("#B71C1C"))
            } else {
                // Lejos -> Fondo verde
                layoutFondo.setBackgroundColor(Color.parseColor("#C8E6C9"))
                tvEstadoProximidad.text = "Objeto Lejos\n($distancia cm)"
                tvEstadoProximidad.setTextColor(Color.parseColor("#1B5E20"))
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
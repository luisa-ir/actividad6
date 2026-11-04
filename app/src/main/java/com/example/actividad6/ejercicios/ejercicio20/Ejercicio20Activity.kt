package com.example.actividad6.ejercicios.ejercicio20

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
import java.util.Locale

class Ejercicio20Activity : AppCompatActivity(), SensorEventListener {

    private lateinit var layoutFondo: View
    private lateinit var tvHeader: TextView
    private lateinit var tvLuzLux: TextView
    private lateinit var tvEstadoLuz: TextView

    private lateinit var sensorManager: SensorManager
    private var lightSensor: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio20)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 20"

        layoutFondo = findViewById(R.id.layoutFondo)
        tvHeader = findViewById(R.id.tvHeader)
        tvLuzLux = findViewById(R.id.tvLuzLux)
        tvEstadoLuz = findViewById(R.id.tvEstadoLuz)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        if (lightSensor == null) {
            tvEstadoLuz.text = "Este dispositivo no cuenta con sensor de luz ambiental."
        }
    }

    override fun onResume() {
        super.onResume()
        lightSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_LIGHT) {
            val lux = event.values[0]
            tvLuzLux.text = String.format(Locale.getDefault(), "Intensidad: %.1f lux", lux)

            when {
                lux < 10.0f -> {
                    layoutFondo.setBackgroundColor(Color.parseColor("#121212"))
                    tvHeader.setTextColor(Color.WHITE)
                    tvLuzLux.setTextColor(Color.WHITE)
                    tvEstadoLuz.setTextColor(Color.parseColor("#B0BEC5"))
                    tvEstadoLuz.text = "Nivel de Luz: Poca luz / Noche"
                }
                lux in 10.0f..500.0f -> {
                    layoutFondo.setBackgroundColor(Color.parseColor("#FFF8E1"))
                    tvHeader.setTextColor(Color.parseColor("#37474F"))
                    tvLuzLux.setTextColor(Color.parseColor("#E65100"))
                    tvEstadoLuz.setTextColor(Color.parseColor("#5D4037"))
                    tvEstadoLuz.text = "Nivel de Luz: Normal / Interior"
                }
                else -> {
                    layoutFondo.setBackgroundColor(Color.parseColor("#FFF59D"))
                    tvHeader.setTextColor(Color.parseColor("#212121"))
                    tvLuzLux.setTextColor(Color.parseColor("#BF360C"))
                    tvEstadoLuz.setTextColor(Color.parseColor("#3E2723"))
                    tvEstadoLuz.text = "Nivel de Luz: Intensa / Sol"
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
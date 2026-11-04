package com.example.actividad6.ejercicios.ejercicio25

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

class Ejercicio25Activity : AppCompatActivity(), SensorEventListener {

    private lateinit var layoutFondo: View
    private lateinit var tvHeader: TextView
    private lateinit var tvEstadoCombinado: TextView

    private lateinit var sensorManager: SensorManager
    private var proximitySensor: Sensor? = null
    private var lightSensor: Sensor? = null

    private var esCerca = false
    private var esOscuro = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio25)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 25"

        layoutFondo = findViewById(R.id.layoutFondo)
        tvHeader = findViewById(R.id.tvHeader)
        tvEstadoCombinado = findViewById(R.id.tvEstadoCombinado)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    override fun onResume() {
        super.onResume()
        proximitySensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
        lightSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return

        if (event.sensor.type == Sensor.TYPE_PROXIMITY) {
            val distancia = event.values[0]
            val maxRange = proximitySensor?.maximumRange ?: 5f
            esCerca = (distancia < maxRange && distancia < 5.0f)
        } else if (event.sensor.type == Sensor.TYPE_LIGHT) {
            val lux = event.values[0]
            esOscuro = (lux < 50.0f)
        }

        actualizarColorPantalla()
    }

    private fun actualizarColorPantalla() {
        if (esOscuro && esCerca) {
            layoutFondo.setBackgroundColor(Color.BLACK)
            tvHeader.setTextColor(Color.WHITE)
            tvEstadoCombinado.setTextColor(Color.WHITE)
            tvEstadoCombinado.text = "Estado: Oscuro y Cerca\n→ Fondo Negro"
        } else if (!esOscuro && !esCerca) {
            layoutFondo.setBackgroundColor(Color.WHITE)
            tvHeader.setTextColor(Color.BLACK)
            tvEstadoCombinado.setTextColor(Color.BLACK)
            tvEstadoCombinado.text = "Estado: Claro y Lejos\n→ Fondo Blanco"
        } else {
            layoutFondo.setBackgroundColor(Color.parseColor("#808080"))
            tvHeader.setTextColor(Color.WHITE)
            tvEstadoCombinado.setTextColor(Color.WHITE)
            tvEstadoCombinado.text = "Estado Intermedio\n(Oscuro: $esOscuro, Cerca: $esCerca)"
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
package com.example.actividad6.ejercicios.ejercicio24

import android.Manifest
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.actividad6.R

class Ejercicio24Activity : AppCompatActivity(), SensorEventListener {

    private lateinit var tvContadorPasos: TextView
    private lateinit var tvEtiquetaPasos: TextView
    private lateinit var btnReiniciarPasos: Button

    private lateinit var sensorManager: SensorManager
    private var stepSensor: Sensor? = null
    private var totalPasos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio24)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 24"

        tvContadorPasos = findViewById(R.id.tvContadorPasos)
        tvEtiquetaPasos = findViewById(R.id.tvEtiquetaPasos)
        btnReiniciarPasos = findViewById(R.id.btnReiniciarPasos)

        btnReiniciarPasos.setOnClickListener {
            totalPasos = 0
            tvContadorPasos.text = "0"
        }

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
            ?: sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (stepSensor == null) {
            tvEtiquetaPasos.text = "Este dispositivo/emulador no cuenta con sensor de pasos."
        }

        verificarPermisos()
    }

    private fun verificarPermisos() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACTIVITY_RECOGNITION),
                    100
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        stepSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return

        if (event.sensor.type == Sensor.TYPE_STEP_DETECTOR) {
            totalPasos += event.values[0].toInt()
            tvContadorPasos.text = totalPasos.toString()
        } else if (event.sensor.type == Sensor.TYPE_STEP_COUNTER) {
            if (totalPasos == 0) {
                totalPasos = 1
            } else {
                totalPasos++
            }
            tvContadorPasos.text = totalPasos.toString()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
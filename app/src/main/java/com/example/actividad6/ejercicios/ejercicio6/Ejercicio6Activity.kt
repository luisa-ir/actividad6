package com.example.actividad6.ejercicios.ejercicio6

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.actividad6.R
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.random.Random

class Ejercicio6Activity : AppCompatActivity() {

    private lateinit var lblLecturaSensor: TextView
    private lateinit var btnActivarSensor: Button
    private lateinit var btnDesactivarSensor: Button

    private var trabajoSensor: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio6)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 6"

        lblLecturaSensor = findViewById(R.id.lblLecturaSensor)
        btnActivarSensor = findViewById(R.id.btnActivarSensor)
        btnDesactivarSensor = findViewById(R.id.btnDesactivarSensor)

        btnActivarSensor.setOnClickListener {
            iniciarTransmisionSensor()
        }

        btnDesactivarSensor.setOnClickListener {
            detenerTransmisionSensor()
        }
    }

    private fun iniciarTransmisionSensor() {
        btnActivarSensor.isEnabled = false
        btnDesactivarSensor.isEnabled = true

        trabajoSensor = lifecycleScope.launch {
            while (isActive) {
                val lectura = Random.nextInt(10, 100)
                lblLecturaSensor.text = "Valor sensor: $lectura Hz"
                delay(500)
            }
        }
    }

    private fun detenerTransmisionSensor() {
        trabajoSensor?.cancel()
        trabajoSensor = null
        btnActivarSensor.isEnabled = true
        btnDesactivarSensor.isEnabled = false
        lblLecturaSensor.text = "Sensor en pausa"
    }

    override fun onPause() {
        super.onPause()
        detenerTransmisionSensor()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
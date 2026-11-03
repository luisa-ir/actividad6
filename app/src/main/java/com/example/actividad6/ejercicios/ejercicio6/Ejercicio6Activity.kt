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

    private lateinit var tvValorSensor: TextView
    private lateinit var btnIniciar: Button
    private lateinit var btnDetener: Button

    private var sensorJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio6)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 6"

        tvValorSensor = findViewById(R.id.tvValorSensor)
        btnIniciar = findViewById(R.id.btnIniciar)
        btnDetener = findViewById(R.id.btnDetener)

        btnIniciar.setOnClickListener {
            iniciarSensor()
        }

        btnDetener.setOnClickListener {
            detenerSensor()
        }
    }

    private fun iniciarSensor() {
        btnIniciar.isEnabled = false
        btnDetener.isEnabled = true

        sensorJob = lifecycleScope.launch {
            while (isActive) {
                val valor = Random.nextInt(10, 100)
                tvValorSensor.text = "Valor del sensor: $valor"
                delay(500)
            }
        }
    }

    private fun detenerSensor() {
        sensorJob?.cancel()
        sensorJob = null
        btnIniciar.isEnabled = true
        btnDetener.isEnabled = false
        tvValorSensor.text = "Sensor detenido"
    }

    override fun onPause() {
        super.onPause()
        detenerSensor()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
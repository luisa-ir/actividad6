package com.example.actividad6.ejercicios.ejercicio5

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.actividad6.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Ejercicio5Activity : AppCompatActivity() {

    private lateinit var btnIniciar: Button
    private lateinit var tvEstado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio5)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 5"

        btnIniciar = findViewById(R.id.btnIniciar)
        tvEstado = findViewById(R.id.tvEstado)

        btnIniciar.setOnClickListener {
            lanzarCorrutinaConsola()
        }
    }

    private fun lanzarCorrutinaConsola() {
        btnIniciar.isEnabled = false
        tvEstado.text = "Corrutina ejecutándose... Revisa Logcat (tag: Ejercicio5)"

        lifecycleScope.launch {
            for (i in 1..5) {
                delay(1000)
                Log.d("Ejercicio5", "Mensaje $i de 5 impreso en consola")
                println("Ejercicio5 - Mensaje $i")
            }
            tvEstado.text = "¡Corrutina finalizada! Se imprimieron 5 mensajes."
            btnIniciar.isEnabled = true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
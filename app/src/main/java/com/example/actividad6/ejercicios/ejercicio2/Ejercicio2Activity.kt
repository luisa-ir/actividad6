package com.example.actividad6.ejercicios.ejercicio2

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.actividad6.R

class Ejercicio2Activity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var tvPorcentaje: TextView
    private lateinit var btnIniciar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio2)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 2"

        progressBar = findViewById(R.id.progressBar)
        tvPorcentaje = findViewById(R.id.tvPorcentaje)
        btnIniciar = findViewById(R.id.btnIniciar)

        btnIniciar.setOnClickListener {
            simularCarga()
        }
    }

    private fun simularCarga() {
        btnIniciar.isEnabled = false
        progressBar.progress = 0
        tvPorcentaje.text = "0%"

        Thread {
            for (i in 0..100) {
                try {
                    Thread.sleep(100)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                runOnUiThread {
                    progressBar.progress = i
                    tvPorcentaje.text = "$i%"
                }
            }

            runOnUiThread {
                btnIniciar.isEnabled = true
            }
        }.start()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
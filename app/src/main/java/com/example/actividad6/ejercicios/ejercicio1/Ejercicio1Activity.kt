package com.example.actividad6.ejercicios.ejercicio1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.actividad6.R

class Ejercicio1Activity : AppCompatActivity() {

    private lateinit var tvResultado: TextView
    private lateinit var btnIniciar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio1)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 1"

        tvResultado = findViewById(R.id.tvResultado)
        btnIniciar = findViewById(R.id.btnIniciar)

        btnIniciar.setOnClickListener {
            iniciarHiloContador()
        }
    }

    private fun iniciarHiloContador() {
        btnIniciar.isEnabled = false

        Thread {
            for (i in 1..10) {
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                runOnUiThread {
                    tvResultado.text = i.toString()
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
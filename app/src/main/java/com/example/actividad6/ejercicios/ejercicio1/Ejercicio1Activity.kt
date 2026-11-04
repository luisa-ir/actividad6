package com.example.actividad6.ejercicios.ejercicio1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.actividad6.R

class Ejercicio1Activity : AppCompatActivity() {

    private lateinit var lblContadorOutput: TextView
    private lateinit var btnComenzarSecuencia: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio1)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 1"

        lblContadorOutput = findViewById(R.id.lblContadorOutput)
        btnComenzarSecuencia = findViewById(R.id.btnComenzarSecuencia)

        btnComenzarSecuencia.setOnClickListener {
            ejecutarSecuenciaHilo()
        }
    }

    private fun ejecutarSecuenciaHilo() {
        btnComenzarSecuencia.isEnabled = false

        Thread {
            for (paso in 1..10) {
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                runOnUiThread {
                    lblContadorOutput.text = "Conteo: $paso / 10"
                }
            }

            runOnUiThread {
                btnComenzarSecuencia.isEnabled = true
            }
        }.start()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
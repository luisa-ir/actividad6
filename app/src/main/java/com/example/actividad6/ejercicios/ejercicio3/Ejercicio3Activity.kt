package com.example.actividad6.ejercicios.ejercicio3

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.actividad6.R

class Ejercicio3Activity : AppCompatActivity() {

    private lateinit var txtHistorialHilos: TextView
    private lateinit var btnComenzarEjecucionHilos: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio3)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 3"

        txtHistorialHilos = findViewById(R.id.txtHistorialHilos)
        btnComenzarEjecucionHilos = findViewById(R.id.btnComenzarEjecucionHilos)

        btnComenzarEjecucionHilos.setOnClickListener {
            lanzarHilosSimultaneos()
        }
    }

    private fun lanzarHilosSimultaneos() {
        btnComenzarEjecucionHilos.isEnabled = false
        txtHistorialHilos.text = "=== Inicio de Ejecución Concurrente ===\n"

        // Hilo 1: Números pares
        val tPares = Thread {
            for (num in 2..20 step 2) {
                try {
                    Thread.sleep((100..280).random().toLong())
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                runOnUiThread {
                    txtHistorialHilos.append("[Hilo-Pares] -> $num\n")
                }
            }
        }

        // Hilo 2: Números impares
        val tImpares = Thread {
            for (num in 1..19 step 2) {
                try {
                    Thread.sleep((100..280).random().toLong())
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                runOnUiThread {
                    txtHistorialHilos.append("[Hilo-Impares] -> $num\n")
                }
            }
        }

        // Hilo 3: Abecedario
        val tLetras = Thread {
            for (letra in 'A'..'J') {
                try {
                    Thread.sleep((100..280).random().toLong())
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                runOnUiThread {
                    txtHistorialHilos.append("[Hilo-Letras] -> $letra\n")
                }
            }
        }

        tPares.start()
        tImpares.start()
        tLetras.start()

        Thread {
            try {
                tPares.join()
                tImpares.join()
                tLetras.join()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            runOnUiThread {
                txtHistorialHilos.append("=== Fin de la Ejecución Concurrente ===\n")
                btnComenzarEjecucionHilos.isEnabled = true
            }
        }.start()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
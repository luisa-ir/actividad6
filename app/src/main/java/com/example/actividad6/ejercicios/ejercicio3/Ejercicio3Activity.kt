package com.example.actividad6.ejercicios.ejercicio3

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.actividad6.R

class Ejercicio3Activity : AppCompatActivity() {

    private lateinit var tvLog: TextView
    private lateinit var btnIniciar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio3)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 3"

        tvLog = findViewById(R.id.tvLog)
        btnIniciar = findViewById(R.id.btnIniciar)

        btnIniciar.setOnClickListener {
            ejecutarTresHilos()
        }
    }

    private fun ejecutarTresHilos() {
        btnIniciar.isEnabled = false
        tvLog.text = "--- Inicio de Ejecución de Hilos ---\n"

        // Hilo 1: Números pares
        val hiloPares = Thread {
            for (i in 2..20 step 2) {
                try {
                    Thread.sleep((100..300).random().toLong())
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                runOnUiThread {
                    tvLog.append("[Par] -> $i\n")
                }
            }
        }

        // Hilo 2: Números impares
        val hiloImpares = Thread {
            for (i in 1..19 step 2) {
                try {
                    Thread.sleep((100..300).random().toLong())
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                runOnUiThread {
                    tvLog.append("[Impar] -> $i\n")
                }
            }
        }

        // Hilo 3: Letras del alfabeto
        val hiloLetras = Thread {
            for (c in 'A'..'J') {
                try {
                    Thread.sleep((100..300).random().toLong())
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                runOnUiThread {
                    tvLog.append("[Letra] -> $c\n")
                }
            }
        }

        hiloPares.start()
        hiloImpares.start()
        hiloLetras.start()

        // Hilo de monitoreo para rehabilitar el botón
        Thread {
            try {
                hiloPares.join()
                hiloImpares.join()
                hiloLetras.join()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            runOnUiThread {
                tvLog.append("--- Fin de la Ejecución ---\n")
                btnIniciar.isEnabled = true
            }
        }.start()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
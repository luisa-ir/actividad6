package com.example.actividad6.ejercicios.ejercicio2

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.actividad6.R

class Ejercicio2Activity : AppCompatActivity() {

    private lateinit var barProgresoCarga: ProgressBar
    private lateinit var txtPorcentajeStatus: TextView
    private lateinit var btnIniciarProcesoCarga: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio2)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 2"

        barProgresoCarga = findViewById(R.id.barProgresoCarga)
        txtPorcentajeStatus = findViewById(R.id.txtPorcentajeStatus)
        btnIniciarProcesoCarga = findViewById(R.id.btnIniciarProcesoCarga)

        btnIniciarProcesoCarga.setOnClickListener {
            ejecutarProcesoProgresivo()
        }
    }

    private fun ejecutarProcesoProgresivo() {
        btnIniciarProcesoCarga.isEnabled = false
        barProgresoCarga.progress = 0
        txtPorcentajeStatus.text = "0 %"

        Thread {
            for (p in 0..100) {
                try {
                    Thread.sleep(100)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                runOnUiThread {
                    barProgresoCarga.progress = p
                    txtPorcentajeStatus.text = "$p %"
                }
            }

            runOnUiThread {
                btnIniciarProcesoCarga.isEnabled = true
            }
        }.start()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
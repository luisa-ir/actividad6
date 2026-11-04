package com.example.actividad6.ejercicios.ejercicio4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.actividad6.R

class Ejercicio4Activity : AppCompatActivity() {

    private lateinit var inputLimiteN: EditText
    private lateinit var btnCalcularSuma: Button
    private lateinit var lblResultadoSuma: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio4)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 4"

        inputLimiteN = findViewById(R.id.inputLimiteN)
        btnCalcularSuma = findViewById(R.id.btnCalcularSuma)
        lblResultadoSuma = findViewById(R.id.lblResultadoSuma)

        btnCalcularSuma.setOnClickListener {
            val valorTexto = inputLimiteN.text.toString()
            if (valorTexto.isEmpty()) {
                Toast.makeText(this, "Ingresa un número entero válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val n = valorTexto.toLongOrNull() ?: 0L
            btnCalcularSuma.isEnabled = false
            lblResultadoSuma.text = "Calculando suma de los primeros $n números..."

            val tareaSuma = MiTarea(n) { total ->
                runOnUiThread {
                    lblResultadoSuma.text = "Suma total (1 a $n):\n$total"
                    btnCalcularSuma.isEnabled = true
                }
            }

            Thread(tareaSuma).start()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
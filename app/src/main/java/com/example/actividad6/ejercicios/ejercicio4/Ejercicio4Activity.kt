package com.example.actividad6.ejercicios.ejercicio4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.actividad6.R

class Ejercicio4Activity : AppCompatActivity() {

    private lateinit var etNumeroN: EditText
    private lateinit var btnCalcular: Button
    private lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio4)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 4"

        etNumeroN = findViewById(R.id.etNumeroN)
        btnCalcular = findViewById(R.id.btnCalcular)
        tvResultado = findViewById(R.id.tvResultado)

        btnCalcular.setOnClickListener {
            val nTexto = etNumeroN.text.toString()
            if (nTexto.isEmpty()) {
                Toast.makeText(this, "Por favor ingresa un número", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val n = nTexto.toLongOrNull() ?: 0L
            btnCalcular.isEnabled = false
            tvResultado.text = "Calculando..."

            val miTarea = MiTarea(n) { resultado ->
                runOnUiThread {
                    tvResultado.text = "Suma de los primeros $n números:\n$resultado"
                    btnCalcular.isEnabled = true
                }
            }

            Thread(miTarea).start()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
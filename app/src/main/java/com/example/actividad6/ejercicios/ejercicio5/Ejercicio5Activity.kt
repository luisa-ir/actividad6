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

    private lateinit var btnDispararCorrutina: Button
    private lateinit var txtEstadoCorrutina: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio5)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 5"

        btnDispararCorrutina = findViewById(R.id.btnDispararCorrutina)
        txtEstadoCorrutina = findViewById(R.id.txtEstadoCorrutina)

        btnDispararCorrutina.setOnClickListener {
            ejecutarConsolaBackground()
        }
    }

    private fun ejecutarConsolaBackground() {
        btnDispararCorrutina.isEnabled = false
        txtEstadoCorrutina.text = "Ejecutando corrutina... Revisa el Logcat de Android Studio."

        lifecycleScope.launch {
            for (i in 1..5) {
                delay(1000)
                Log.d("Ejercicio5", "Mensaje $i impreso desde corrutina")
                println("Consola Ejercicio5 -> Mensaje $i")
            }
            txtEstadoCorrutina.text = "Proceso finalizado. 5 mensajes impresos en consola."
            btnDispararCorrutina.isEnabled = true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
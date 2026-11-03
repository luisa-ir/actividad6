package com.example.actividad6.ejercicios.ejercicio11

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.actividad6.R

class Ejercicio11Activity : AppCompatActivity() {

    private lateinit var vistaDibujo: VistaDibujo
    private lateinit var btnLimpiar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio11)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 11"

        vistaDibujo = findViewById(R.id.vistaDibujo)
        btnLimpiar = findViewById(R.id.btnLimpiar)

        btnLimpiar.setOnClickListener {
            vistaDibujo.limpiarLienzo()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
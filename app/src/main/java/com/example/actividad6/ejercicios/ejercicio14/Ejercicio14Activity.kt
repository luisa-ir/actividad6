package com.example.actividad6.ejercicios.ejercicio14

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.actividad6.R

class Ejercicio14Activity : AppCompatActivity() {

    private lateinit var vistaMultitactil: VistaDibujoMultitactil
    private lateinit var btnLimpiar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio14)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 14"

        vistaMultitactil = findViewById(R.id.vistaMultitactil)
        btnLimpiar = findViewById(R.id.btnLimpiar)

        btnLimpiar.setOnClickListener {
            vistaMultitactil.limpiarLienzo()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
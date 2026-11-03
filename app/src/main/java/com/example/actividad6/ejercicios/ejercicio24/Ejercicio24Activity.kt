package com.example.actividad6.ejercicios.ejercicio24

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.actividad6.R

class Ejercicio24Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 24"
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
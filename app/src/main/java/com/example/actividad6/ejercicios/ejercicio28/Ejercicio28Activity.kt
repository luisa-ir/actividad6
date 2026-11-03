package com.example.actividad6.ejercicios.ejercicio28

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.actividad6.R

class Ejercicio28Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio28)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 28"
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
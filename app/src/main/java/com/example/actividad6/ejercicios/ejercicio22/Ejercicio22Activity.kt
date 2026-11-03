package com.example.actividad6.ejercicios.ejercicio22

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.actividad6.R

class Ejercicio22Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio22)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 22"
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
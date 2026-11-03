package com.example.actividad6.ejercicios.ejercicio27

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.actividad6.R

class Ejercicio27Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio27)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 27"
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
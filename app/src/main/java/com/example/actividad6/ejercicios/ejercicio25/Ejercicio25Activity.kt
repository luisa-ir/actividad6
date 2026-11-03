package com.example.actividad6.ejercicios.ejercicio25

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.actividad6.R

class Ejercicio25Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio25)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 25"
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
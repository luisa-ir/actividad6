package com.example.actividad6

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rvEjercicios: RecyclerView = findViewById(R.id.rvEjercicios)
        rvEjercicios.layoutManager = GridLayoutManager(this, 2)
        rvEjercicios.adapter = EjerciciosAdapter(28) { numeroEjercicio ->
            val className = "com.example.actividad6.ejercicios.ejercicio$numeroEjercicio.Ejercicio${numeroEjercicio}Activity"
            try {
                val clazz = Class.forName(className)
                startActivity(Intent(this, clazz))
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            }
        }
    }

    private class EjerciciosAdapter(
        private val count: Int,
        private val onItemClick: (Int) -> Unit
    ) : RecyclerView.Adapter<EjerciciosAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val btnEjercicio: Button = view.findViewById(R.id.btnEjercicio)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_ejercicio_button, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val numeroEjercicio = position + 1
            holder.btnEjercicio.text = "Ejercicio $numeroEjercicio"
            holder.btnEjercicio.setOnClickListener {
                onItemClick(numeroEjercicio)
            }
        }

        override fun getItemCount(): Int = count
    }
}
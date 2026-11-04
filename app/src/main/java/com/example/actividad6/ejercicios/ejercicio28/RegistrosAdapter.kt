package com.example.actividad6.ejercicios.ejercicio28

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.actividad6.R

class RegistrosAdapter(
    private val lista: List<RegistroSensor>
) : RecyclerView.Adapter<RegistrosAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTimestamp: TextView = view.findViewById(R.id.tvTimestamp)
        val tvPasosItem: TextView = view.findViewById(R.id.tvPasosItem)
        val tvMovimientoItem: TextView = view.findViewById(R.id.tvMovimientoItem)
        val tvGiroscopioItem: TextView = view.findViewById(R.id.tvGiroscopioItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_registro_sensor, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lista[position]
        holder.tvTimestamp.text = item.timestamp
        holder.tvPasosItem.text = "Pasos: ${item.pasos}"
        holder.tvMovimientoItem.text = "Movimiento: ${item.nivelMovimiento}"
        holder.tvGiroscopioItem.text = "Giroscopio: ${item.giroscopioInfo}"
    }

    override fun getItemCount(): Int = lista.size
}
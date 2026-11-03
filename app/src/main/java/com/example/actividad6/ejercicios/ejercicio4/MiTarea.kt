package com.example.actividad6.ejercicios.ejercicio4

class MiTarea(
    private val n: Long,
    private val onResultado: (Long) -> Unit
) : Runnable {
    override fun run() {
        var suma: Long = 0
        for (i in 1..n) {
            suma += i
        }
        onResultado(suma)
    }
}
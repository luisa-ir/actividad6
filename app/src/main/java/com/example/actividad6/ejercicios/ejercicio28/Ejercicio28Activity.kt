package com.example.actividad6.ejercicios.ejercicio28

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.actividad6.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.sqrt

class Ejercicio28Activity : AppCompatActivity(), SensorEventListener {

    private lateinit var tvResumenActual: TextView
    private lateinit var btnGuardarRegistro: Button
    private lateinit var rvRegistros: RecyclerView

    private lateinit var sensorManager: SensorManager
    private var stepSensor: Sensor? = null
    private var accelerometer: Sensor? = null
    private var gyroscope: Sensor? = null

    private var totalPasos = 0
    private var nivelMovimientoStr = "Reposo"
    private var giroscopioInfoStr = "X: 0.0, Y: 0.0, Z: 0.0 rad/s"

    private val listaRegistros = mutableListOf<RegistroSensor>()
    private lateinit var adapter: RegistrosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio28)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 28"

        tvResumenActual = findViewById(R.id.tvResumenActual)
        btnGuardarRegistro = findViewById(R.id.btnGuardarRegistro)
        rvRegistros = findViewById(R.id.rvRegistros)

        adapter = RegistrosAdapter(listaRegistros)
        rvRegistros.layoutManager = LinearLayoutManager(this)
        rvRegistros.adapter = adapter

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
            ?: sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        btnGuardarRegistro.setOnClickListener {
            guardarMuestra()
        }
    }

    private fun guardarMuestra() {
        val horaActual = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        val muestra = RegistroSensor(
            timestamp = horaActual,
            pasos = totalPasos,
            nivelMovimiento = nivelMovimientoStr,
            giroscopioInfo = giroscopioInfoStr
        )
        listaRegistros.add(0, muestra)
        adapter.notifyItemInserted(0)
        rvRegistros.scrollToPosition(0)
    }

    override fun onResume() {
        super.onResume()
        stepSensor?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI) }
        accelerometer?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI) }
        gyroscope?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI) }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return

        when (event.sensor.type) {
            Sensor.TYPE_STEP_DETECTOR -> {
                totalPasos += event.values[0].toInt()
            }
            Sensor.TYPE_STEP_COUNTER -> {
                totalPasos++
            }
            Sensor.TYPE_ACCELEROMETER -> {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]
                val mag = sqrt((x * x + y * y + z * z).toDouble()).toFloat()
                nivelMovimientoStr = when {
                    mag < 11.5f -> "Reposo (%.1f m/s²)".format(Locale.getDefault(), mag)
                    mag in 11.5f..16.0f -> "Leve (%.1f m/s²)".format(Locale.getDefault(), mag)
                    else -> "Intenso (%.1f m/s²)".format(Locale.getDefault(), mag)
                }
            }
            Sensor.TYPE_GYROSCOPE -> {
                val gx = event.values[0]
                val gy = event.values[1]
                val gz = event.values[2]
                giroscopioInfoStr = String.format(Locale.getDefault(), "X:%.2f Y:%.2f Z:%.2f rad/s", gx, gy, gz)
            }
        }

        tvResumenActual.text = "Pasos: $totalPasos | Movimiento: $nivelMovimientoStr\n$giroscopioInfoStr"
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
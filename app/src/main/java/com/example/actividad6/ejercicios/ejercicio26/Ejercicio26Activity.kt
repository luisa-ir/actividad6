package com.example.actividad6.ejercicios.ejercicio26

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.actividad6.R
import java.util.Locale
import kotlin.math.sqrt

class Ejercicio26Activity : AppCompatActivity(), SensorEventListener {

    private lateinit var tvEstadoShake: TextView
    private lateinit var tvMagnitudAceleracion: TextView

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null

    private val handler = Handler(Looper.getMainLooper())
    private val resetRunnable = Runnable {
        tvEstadoShake.text = "Sacude el dispositivo..."
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio26)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 26"

        tvEstadoShake = findViewById(R.id.tvEstadoShake)
        tvMagnitudAceleracion = findViewById(R.id.tvMagnitudAceleracion)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onResume() {
        super.onResume()
        accelerometer?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
        handler.removeCallbacks(resetRunnable)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            val magnitud = sqrt((x * x + y * y + z * z).toDouble()).toFloat()
            tvMagnitudAceleracion.text = String.format(Locale.getDefault(), "Aceleración actual: %.1f m/s²", magnitud)

            // Umbral de agitación (magnitud > 18.0 m/s², superando la gravedad de 9.8 m/s²)
            if (magnitud > 18.0f) {
                tvEstadoShake.text = "¡Agitado!"
                handler.removeCallbacks(resetRunnable)
                handler.postDelayed(resetRunnable, 1500)
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
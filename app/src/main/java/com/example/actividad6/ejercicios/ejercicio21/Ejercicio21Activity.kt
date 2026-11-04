package com.example.actividad6.ejercicios.ejercicio21

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.actividad6.R
import java.util.Locale

class Ejercicio21Activity : AppCompatActivity(), SensorEventListener {

    private lateinit var tvAzimut: TextView
    private lateinit var tvPitch: TextView
    private lateinit var tvRoll: TextView

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private var magnetometer: Sensor? = null

    private var gravity = FloatArray(3)
    private var geomagnetic = FloatArray(3)
    private var hasGravity = false
    private var hasGeomagnetic = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio21)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ejercicio 21"

        tvAzimut = findViewById(R.id.tvAzimut)
        tvPitch = findViewById(R.id.tvPitch)
        tvRoll = findViewById(R.id.tvRoll)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
    }

    override fun onResume() {
        super.onResume()
        accelerometer?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
        magnetometer?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return

        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            System.arraycopy(event.values, 0, gravity, 0, 3)
            hasGravity = true
        } else if (event.sensor.type == Sensor.TYPE_MAGNETIC_FIELD) {
            System.arraycopy(event.values, 0, geomagnetic, 0, 3)
            hasGeomagnetic = true
        }

        if (hasGravity && hasGeomagnetic) {
            val rMatrix = FloatArray(9)
            val iMatrix = FloatArray(9)
            val success = SensorManager.getRotationMatrix(rMatrix, iMatrix, gravity, geomagnetic)
            if (success) {
                val orientation = FloatArray(3)
                SensorManager.getOrientation(rMatrix, orientation)

                val azimutDeg = (Math.toDegrees(orientation[0].toDouble()) + 360) % 360
                val pitchDeg = Math.toDegrees(orientation[1].toDouble())
                val rollDeg = Math.toDegrees(orientation[2].toDouble())

                tvAzimut.text = String.format(Locale.getDefault(), "Azimut (Brújula): %.1f°", azimutDeg)
                tvPitch.text = String.format(Locale.getDefault(), "Pitch (Inclinación frontal): %.1f°", pitchDeg)
                tvRoll.text = String.format(Locale.getDefault(), "Roll (Inclinación lateral): %.1f°", rollDeg)
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
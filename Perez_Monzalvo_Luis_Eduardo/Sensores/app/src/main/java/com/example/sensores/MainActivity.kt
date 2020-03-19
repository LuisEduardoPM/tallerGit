package com.example.sensores

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity(), SensorEventListener {

    private var deviceSensors: List<Sensor> = emptyList()
    private var sensor: Sensor? = null
    private lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        if(sensor == null)
            println("TYPE_ACCELEROMETER no soportado")

        deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL)
        deviceSensors.forEach {
            println(it)
        }
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER){
            var x = event.values[0]
            var y = event.values[1]
            var z = event.values[2]

            println("X: " + x + " Y: " + y + " Z:" + z)
            setContentView(R.layout.activity_main)
            val textView = findViewById(R.id.kalu1) as TextView
            textView.text = "X = " + event.values[0]

            val textView2 = findViewById(R.id.kalu2) as TextView
            textView2.text = "Y = " + event.values[1]

            val textView3 = findViewById(R.id.kalu3) as TextView
            textView3.text = "Z = " + event.values[2]




        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) { }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
    }
}

package fr.esgi.trainstation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.content.Context
import android.hardware.SensorEvent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import fr.esgi.trainstation.activityListeGare.ActivityListeGare

class HomeActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var listeGareButton: Button
    private lateinit var locationTextView: TextView

    private lateinit var sensorManager: SensorManager
    private var gyroscopeSensor: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        listeGareButton = findViewById(R.id.listeApi)
        locationTextView = findViewById(R.id.textView)

        listeGareButton.setOnClickListener {
            val intent = Intent(this, ActivityListeGare::class.java)

            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            requestLocation { latitude, longitude ->
                intent.putExtra("latitude", latitude.toString())
                intent.putExtra("longitude", longitude.toString())
                startActivity(intent)
            }
        }
    }

    private fun requestLocation(locationCallback: (latitude: Double, longitude: Double) -> Unit) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1001
            )
        } else {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        locationCallback(location.latitude, location.longitude)
                    } else {
                        Toast.makeText(
                            this,
                            "Impossible de récupérer la localisation actuelle",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(
                        this,
                        "Erreur lors de la récupération de la localisation: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }

    override fun onResume() {
        super.onResume()
        gyroscopeSensor?.also { sensor ->
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_GYROSCOPE) {
            val rotationDegrees = Math.toDegrees(event.values[2].toDouble()).toFloat()
            findViewById<ImageView>(R.id.compass_arrow).rotation = rotationDegrees
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}

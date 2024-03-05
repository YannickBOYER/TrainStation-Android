package fr.esgi.trainstation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import fr.esgi.trainstation.activityListeGare.ActivityListeGare

class HomeActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationButton: Button
    private lateinit var listeGareButton: Button
    private lateinit var locationTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        locationButton = findViewById(R.id.getLocation)
        listeGareButton = findViewById(R.id.listeApi)
        locationTextView = findViewById(R.id.textView)

        locationButton.setOnClickListener {
            requestLocation()
        }

        listeGareButton.setOnClickListener {
            val intent = Intent(this, ActivityListeGare::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(intent)
        }


    }

    private fun requestLocation() {
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
                        val latitude = location.latitude
                        val longitude = location.longitude
                        val message = "Latitude: $latitude, Longitude: $longitude"
                        locationTextView.text = message
                    } else {
                        locationTextView.text = "Impossible de récupérer la localisation actuelle"
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
}

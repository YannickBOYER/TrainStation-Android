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
    private lateinit var listeGareButton: Button
    private lateinit var locationTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)


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
}

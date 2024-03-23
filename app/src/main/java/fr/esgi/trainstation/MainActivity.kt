package fr.esgi.trainstation

import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import fr.esgi.trainstation.databinding.ActivityMainBinding
import android.os.Looper
import android.widget.Toast
import android.content.Context
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Vérifier la connexion Internet lors du lancement de l'activité
        if(isUtilisateurConnecteInternet()){
            binding = ActivityMainBinding.inflate(layoutInflater)
            binding.lottieAnimationView.setAnimation(R.raw.animation_start)
            binding.lottieAnimationView.playAnimation()

            setContentView(binding.root)

            // 5000 la bonne valeurHomeActivity
            val animationScreenTimeOut = 5000
            val homeIntent = Intent(this, HomeActivity::class.java)

            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(homeIntent)
                finish()
            }, animationScreenTimeOut.toLong())
        }
    }

    private fun isUtilisateurConnecteInternet(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)

        return if (capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))) {
            Toast.makeText(this, this.getString(R.string.label_connexion_activee), Toast.LENGTH_SHORT).show()
            true
        } else {
            AlertDialog.Builder(this)
                .setMessage(this.getString(R.string.message_activer_internet))
                .setPositiveButton(this.getString(R.string.label_ok)) { dialog, _ ->
                    dialog.dismiss()
                }
                .setCancelable(false)
                .show()
            false
        }
    }
}

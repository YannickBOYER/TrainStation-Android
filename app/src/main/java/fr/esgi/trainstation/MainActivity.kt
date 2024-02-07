package fr.esgi.trainstation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import fr.esgi.trainstation.databinding.ActivityMainBinding
import android.os.Looper

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

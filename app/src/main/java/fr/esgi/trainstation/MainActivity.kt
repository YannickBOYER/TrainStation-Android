package fr.esgi.trainstation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.esgi.trainstation.databinding.ActivityMainBinding
import fr.esgi.trainstation.rptTestActivity.ActivityListeGare

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.btnLottie.setOnClickListener{
            val intent = Intent(this, ActivityListeGare::class.java)
            print(intent.action);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(intent)
        }

        setContentView(binding.root)
    }

}
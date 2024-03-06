package fr.esgi.trainstation.activityDetailGare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.esgi.trainstation.R
import fr.esgi.trainstation.databinding.ActivityDetailGareBinding
import fr.esgi.trainstation.databinding.ActivityListeGaresBinding

class ActivityDetailGare : AppCompatActivity() {
    private lateinit var binding: ActivityDetailGareBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailGareBinding.inflate(layoutInflater)
        binding.libelle.text = intent.getStringExtra("gareLibelle")
        setContentView(binding.root)
    }
}
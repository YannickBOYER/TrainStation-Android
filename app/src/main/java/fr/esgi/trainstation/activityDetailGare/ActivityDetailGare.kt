package fr.esgi.trainstation.activityDetailGare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import fr.esgi.trainstation.R
import fr.esgi.trainstation.activityListeGare.ListeGareViewModel
import fr.esgi.trainstation.databinding.ActivityDetailGareBinding
import fr.esgi.trainstation.databinding.ActivityListeGaresBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ActivityDetailGare : AppCompatActivity() {
    private lateinit var binding: ActivityDetailGareBinding
    private val detailGareViewModel: DetailGareViewModel by viewModels()

    private var nomGare = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailGareBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gareLibelle = intent.getStringExtra("nomGare")
        if(gareLibelle != null){
            this.nomGare = gareLibelle
            detailGareViewModel.gare.observe(this){ gare ->
                binding.libelle.text = gare.nom
            }
        }else{
            binding.libelle.text = gareLibelle
            // TODO: Aucune info à propos de la gare.
        }
    }

    override fun onResume() {
        super.onResume()
        detailGareViewModel.getGareDetailsFromNom(this.nomGare)
    }
}
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
    private val nonRenseigne = "Non renseigné."

    private var nomGare = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailGareBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gareLibelle = intent.getStringExtra("nomGare")
        if(gareLibelle != null){
            this.nomGare = gareLibelle
            detailGareViewModel.gare.observe(this){ gare ->
                if(gare.details.total_count > 0){
                    binding.libelle.text = gare.details.results[0].nom ?: nonRenseigne
                    binding.libCourt.text = gare.details.results[0].libellecourt ?: nonRenseigne
                    binding.codeInsee.text = gare.details.results[0].codeinsee ?: nonRenseigne

                    //binding.libelle.text = gare.details.results[0].codeinsee ?: nonRenseigne
                    // binding.libelle.text = gare.details.results[0].position_geographique ?: nonRenseigne
                }
                if(gare.gareEquipeeWifiResponse.total_count > 0){
                    binding.wifi.text = gare.gareEquipeeWifiResponse.results[0].service_wifi ?: nonRenseigne
                }
                /*binding.libelle.text = gare.nom
                if(gare.gareEquipeeWifiResponse.total_count > 0){
                    binding.equipeeWifi.text = gare.gareEquipeeWifiResponse.results[0].service_wifi;
                }else{
                    binding.equipeeWifi.text = nonRenseigne
                }*/
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
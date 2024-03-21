package fr.esgi.trainstation.activityDetailGare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import fr.esgi.trainstation.R
import fr.esgi.trainstation.activityDetailGare.Adapter.AssistanceAdapter
import fr.esgi.trainstation.activityDetailGare.Adapter.HoraireAdapter
import fr.esgi.trainstation.activityDetailGare.ApiResponse.EnqueteResult
import fr.esgi.trainstation.databinding.ActivityDetailGareBinding

class ActivityDetailGare : AppCompatActivity() {
    private lateinit var binding: ActivityDetailGareBinding
    private val detailGareViewModel: DetailGareViewModel by viewModels()
    private val assistanceAdapter = AssistanceAdapter(this)
    private val horaireAdapter = HoraireAdapter(this)

    private var nonRenseigne = ""
    private var nomGare = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailGareBinding.inflate(layoutInflater)

        binding.recyclerViewAssistancePmr.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewAssistancePmr.adapter = assistanceAdapter

        binding.recyclerViewHoraire.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewHoraire.adapter = horaireAdapter

        setContentView(binding.root)

        val gareLibelle = intent.getStringExtra("nomGare")
        if(gareLibelle != null){
            this.nomGare = gareLibelle
            detailGareViewModel.gareInformationsGenerales.observe(this){ gare ->
                setInformationGeneralesFromGare(gare)
                setRepartitionFromGare(gare)
            }

            detailGareViewModel.assistancePMR.observe(this) { assistances ->
                if(assistances.isEmpty()){
                    binding.recyclerViewAssistancePmr.visibility = View.GONE
                    binding.aucuneAssistancePmr.visibility = View.VISIBLE
                }else{
                    assistanceAdapter.loadData(assistances)
                }
            }

            detailGareViewModel.horaires.observe(this){ horaires ->
                if(horaires.isEmpty()){
                    binding.recyclerViewHoraire.visibility = View.GONE
                    binding.aucunHoraire.visibility = View.VISIBLE
                }else{
                    horaireAdapter.loadData(horaires)
                }
            }


        }else{
            binding.libelle.text = gareLibelle
            // TODO: Aucune info à propos de la gare.
        }
    }

    override fun onResume() {
        super.onResume()
        nonRenseigne = getString(R.string.label_non_renseigne)
        detailGareViewModel.getInformationGeneralesFromNom(this.nomGare)
        detailGareViewModel.getAssistancesPMRFromNom(this.nomGare)
        detailGareViewModel.getHorairesFromNom(this.nomGare)
    }

    fun setInformationGeneralesFromGare(gare: GareInformationGeneraleModel){
        if(gare.details.total_count > 0){
            binding.libelle.text = gare.details.results[0].nom ?: nonRenseigne
            binding.libCourt.text = gare.details.results[0].libellecourt ?: nonRenseigne
            binding.codeInsee.text = gare.details.results[0].codeinsee ?: nonRenseigne
        }
        if(gare.gareEquipeeWifiResponse.total_count > 0){
            binding.wifi.text = gare.gareEquipeeWifiResponse.results[0].service_wifi ?: nonRenseigne
        }else{
            binding.wifi.text = nonRenseigne
        }
    }

    fun setRepartitionFromGare(gare: GareInformationGeneraleModel){
        if(gare.repartitionHFResponse.total_count > 0){
            val resultats: List<EnqueteResult>  = gare.repartitionHFResponse.results
            val resultat0: String = resultats[0].pourcentage.toString().plus("%").plus("(${resultats[0].sexe})")
            val resultat1: String = resultats[1].pourcentage.toString().plus("%").plus("(${resultats[1].sexe})")
            binding.repartitionHF.text = resultat0.plus(" / $resultat1")
        }else{
            binding.repartitionHF.text = nonRenseigne
        }
    }
}
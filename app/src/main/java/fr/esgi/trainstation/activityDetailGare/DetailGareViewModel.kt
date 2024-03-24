package fr.esgi.trainstation.activityDetailGare

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.esgi.trainstation.activityDetailGare.ApiResponse.AccompagnementPMRResponse
import fr.esgi.trainstation.activityDetailGare.ApiResponse.Assistance
import fr.esgi.trainstation.activityDetailGare.ApiResponse.GareDetailResponse
import fr.esgi.trainstation.activityDetailGare.ApiResponse.GareEquipeeWifiResponse
import fr.esgi.trainstation.activityDetailGare.ApiResponse.Horaire
import fr.esgi.trainstation.activityDetailGare.ApiResponse.HoraireGareResponse
import fr.esgi.trainstation.activityDetailGare.ApiResponse.RepartitionHFResponse
import kotlinx.coroutines.launch

class DetailGareViewModel : ViewModel() {
    private val listeGareRepository = DetailGareRepository()
    private val _gareInformationsGenerales = MutableLiveData<GareInformationGeneraleModel>()
    private val _assistancesPMR = MutableLiveData<List<Assistance>>()
    private val _horaires = MutableLiveData<List<Horaire>>()

    val gareInformationsGenerales: LiveData<GareInformationGeneraleModel> = _gareInformationsGenerales
    val assistancePMR: LiveData<List<Assistance>> = _assistancesPMR
    val horaires: LiveData<List<Horaire>> = _horaires

    fun getInformationGeneralesFromNom(nom: String) {
        viewModelScope.launch {
            val details: GareDetailResponse = listeGareRepository.getDetailsFromNomGare(nom)
            val gareEquipeeWifiResponse: GareEquipeeWifiResponse = listeGareRepository.getGareEquipeeWifiFromNomGare(nom)
            val repartitionHFResponse: RepartitionHFResponse = listeGareRepository.getRepartitionHFFromNomGare(nom)
            _gareInformationsGenerales.value = GareInformationGeneraleModel(details, gareEquipeeWifiResponse, repartitionHFResponse)
        }
    }

    fun getAssistancesPMRFromNom(nom: String){
        viewModelScope.launch {
            val accompagnementPMR: AccompagnementPMRResponse = listeGareRepository.getAccompagnmentPMRFromNomGare(nom)
            _assistancesPMR.value = accompagnementPMR.results
        }
    }

    fun getHorairesFromNom(nom: String){
        viewModelScope.launch {
            val horaires: HoraireGareResponse = listeGareRepository.getHorairesFromNomGare(nom)
            _horaires.value = horaires.results
        }
    }
}
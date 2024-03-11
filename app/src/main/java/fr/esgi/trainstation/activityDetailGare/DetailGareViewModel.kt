package fr.esgi.trainstation.activityDetailGare

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.esgi.trainstation.activityDetailGare.ApiResponse.HoraireGareResponse
import kotlinx.coroutines.launch

class DetailGareViewModel : ViewModel() {
    private val listeGareRepository = DetailGareRepository()
    private val _gare = MutableLiveData<GareDetailModel>()
    val gare: LiveData<GareDetailModel> = _gare

    fun getGareDetailsFromNom(nom: String) {
        viewModelScope.launch {
            val horaires: HoraireGareResponse = listeGareRepository.getHorairesFromGare(nom)
            _gare.value = GareDetailModel(nom, horaires)
        }
    }
}
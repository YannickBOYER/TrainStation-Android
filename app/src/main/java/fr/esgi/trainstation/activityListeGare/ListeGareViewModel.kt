package fr.esgi.trainstation.activityListeGare
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope;
import kotlinx.coroutines.launch


class ListeGareViewModel : ViewModel() {
    private val listeGareRepository = ListeGareRepository()
    private val _records = MutableLiveData<List<Record>>()
    val records: LiveData<List<Record>> = _records

    fun fetchGaresFromLocation(latitude: String, longitude: String) {
        viewModelScope.launch {
            _records.value = listeGareRepository.getGaresFromLocation(latitude, longitude).records
        }
    }
}
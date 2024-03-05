package fr.esgi.trainstation.activityListeGare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.esgi.trainstation.databinding.ActivityListeGaresBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ActivityListeGare : AppCompatActivity() {

    private lateinit var binding: ActivityListeGaresBinding
    var records:List<Record> = mutableListOf()
    var adapter = RecordAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListeGaresBinding.inflate(layoutInflater)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        MainScope().launch {
            records = getGareFromLocation()
            adapter.loadData(records)
        }
    }

    private suspend fun getGareFromLocation():List<Record> {
        val gares = ListeGareApi.getGaresFromLocation()
        return gares.records
    }
}
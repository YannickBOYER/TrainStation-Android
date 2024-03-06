package fr.esgi.trainstation.activityListeGare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.esgi.trainstation.databinding.ActivityListeGaresBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import androidx.activity.viewModels

class ActivityListeGare : AppCompatActivity(), OnGareClickListener {

    private lateinit var binding: ActivityListeGaresBinding
    private val listeGareViewModel: ListeGareViewModel by viewModels()
    private val adapter = RecordAdapter(this)

    override fun onClick(record: Record) {
        print(record)
        TODO("Redirection sur le détail")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListeGaresBinding.inflate(layoutInflater)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        setContentView(binding.root)

        listeGareViewModel.records.observe(this) { records ->
            adapter.loadData(records)
        }
    }

    override fun onResume() {
        super.onResume()
        listeGareViewModel.fetchGaresFromLocation()
    }
}
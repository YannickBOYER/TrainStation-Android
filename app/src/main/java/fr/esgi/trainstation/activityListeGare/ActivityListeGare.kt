package fr.esgi.trainstation.activityListeGare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.esgi.trainstation.databinding.ActivityListeGaresBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import androidx.activity.viewModels
import fr.esgi.trainstation.activityDetailGare.ActivityDetailGare

class ActivityListeGare : AppCompatActivity(), OnGareClickListener {

    private lateinit var binding: ActivityListeGaresBinding
    private val listeGareViewModel: ListeGareViewModel by viewModels()
    private val adapter = RecordAdapter(this)

    override fun onClick(record: Record) {
        val intentDetailGare = Intent(this, ActivityDetailGare::class.java)
        intentDetailGare.putExtra("gareLibelle", record.fields.libelle)
        startActivity(intentDetailGare)
    }

    override fun onClickMap(record: Record) {
        TODO("Not yet implemented")
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
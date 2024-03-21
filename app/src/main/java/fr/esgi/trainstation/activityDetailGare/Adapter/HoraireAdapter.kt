package fr.esgi.trainstation.activityDetailGare.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.esgi.trainstation.R
import fr.esgi.trainstation.activityDetailGare.ApiResponse.Horaire
import fr.esgi.trainstation.databinding.ItemHoraireBinding

class HoraireAdapter(var context: Context): RecyclerView.Adapter<HoraireAdapter.HoraireViewHolder>() {
    var horaires : MutableList<Horaire> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoraireViewHolder {
        val binding = ItemHoraireBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HoraireViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return horaires.size
    }

    override fun onBindViewHolder(holder: HoraireViewHolder, position: Int) {
        val nonRenseigne: String = context.getString(R.string.label_non_renseigne)
        val horaire = horaires[position]
        holder.binding.jour.text = horaire.jour ?: nonRenseigne
        holder.binding.horaires.text = horaire.horaire_normal ?: nonRenseigne
        holder.binding.horairesJoursFeries.text = horaire.horaire_ferie ?: nonRenseigne
    }

    fun loadData(horaires:List<Horaire>){
        this.horaires.clear()
        this.horaires.addAll(horaires)
        notifyDataSetChanged()
    }

    class HoraireViewHolder(val binding: ItemHoraireBinding) : RecyclerView.ViewHolder(binding.root)
}
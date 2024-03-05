package fr.esgi.trainstation.activityListeGare

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.esgi.trainstation.databinding.LayoutGaresBinding

class RecordAdapter: RecyclerView.Adapter<RecordAdapter.GareViewHolder>() {
    var records:MutableList<Record> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GareViewHolder {
        val binding = LayoutGaresBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GareViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return records.size
    }

    override fun onBindViewHolder(holder: GareViewHolder, position: Int) {
        val gare = records[position]
        holder.binding.layoutGaresLibelle.text = gare.fields.libelle

    }

    fun loadData(records:List<Record>){
        this.records.clear()
        this.records.addAll(records)
        notifyDataSetChanged()
    }

    class GareViewHolder(val binding: LayoutGaresBinding) : RecyclerView.ViewHolder(binding.root)

}
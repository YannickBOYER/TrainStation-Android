package fr.esgi.trainstation.activityListeGare

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.esgi.trainstation.databinding.LayoutGaresBinding
import io.ktor.util.toLowerCasePreservingASCIIRules

class RecordAdapter(var callback: OnGareClickListener): RecyclerView.Adapter<RecordAdapter.GareViewHolder>() {
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
        holder.binding.libelle.text = gare.fields.nom
        holder.binding.location.text = gare.fields.libellecourt.plus(" ").plus(gare.fields.codeinsee)

        holder.binding.gareContainer.setOnClickListener{
            callback.onClick(gare)
        }

        // On click sur l'icon map
        holder.binding.itemGare.setOnClickListener{
            callback.onClickMap(gare)
        }
    }

    fun loadData(records:List<Record>){
        this.records.clear()
        this.records.addAll(records)
        notifyDataSetChanged()
    }

    class GareViewHolder(val binding: LayoutGaresBinding) : RecyclerView.ViewHolder(binding.root)
}

interface OnGareClickListener{
    fun onClick(record: Record)

    fun onClickMap(record: Record)
}
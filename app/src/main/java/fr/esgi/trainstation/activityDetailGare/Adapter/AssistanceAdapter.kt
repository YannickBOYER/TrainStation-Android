package fr.esgi.trainstation.activityDetailGare.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import fr.esgi.trainstation.R
import fr.esgi.trainstation.activityDetailGare.ApiResponse.Assistance
import fr.esgi.trainstation.databinding.ItemAssistancePmrBinding

class AssistanceAdapter(var context: Context): RecyclerView.Adapter<AssistanceAdapter.AssistancePMRViewHolder>() {
    var assistances : MutableList<Assistance> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssistancePMRViewHolder {
        val binding = ItemAssistancePmrBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AssistancePMRViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return assistances.size
    }

    override fun onBindViewHolder(holder: AssistancePMRViewHolder, position: Int) {
        val nonRenseigne: String = context.getString(R.string.label_non_renseigne)
        val assistance = assistances[position]
        holder.binding.dateMensuelle.text = assistance.datemensuel ?: nonRenseigne
        holder.binding.nbrFauteuil.text = assistance.fauteuil.toString() ?: nonRenseigne
        holder.binding.nbrRampe.text = assistance.rampe.toString() ?: nonRenseigne
    }

    fun loadData(assistance:List<Assistance>){
        this.assistances.clear()
        this.assistances.addAll(assistance)
        notifyDataSetChanged()
    }

    class AssistancePMRViewHolder(val binding: ItemAssistancePmrBinding) : RecyclerView.ViewHolder(binding.root)
}
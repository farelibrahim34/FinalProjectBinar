package com.finpro.garudanih.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finpro.garudanih.databinding.ItemBinding
import com.finpro.garudanih.databinding.ItemInternasionalBinding
import com.finpro.garudanih.model.ListInternasional
import com.finpro.garudanih.model.ListPesawat
import com.finpro.garudanih.view.detils.DetailInternasionalActivity
import com.finpro.garudanih.view.detils.DetailPesawatActivity

class AdapterInternasional(val listInternasional : ArrayList<ListInternasional>): RecyclerView.Adapter<AdapterInternasional.ViewHolder>() {

    class ViewHolder (var binding : ItemInternasionalBinding):RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemInternasionalBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtKota.text = listInternasional[position].kotaInt
        holder.binding.txtJadwal.text = listInternasional[position].jadwalInt
        holder.binding.txtHarga.text = listInternasional[position].hargaInt.toString()
        holder.binding.ivListpesawat.setImageResource(listInternasional[position].gambarInt)
        holder.binding.txtAvailable.text = listInternasional[position].availableInt

        holder.binding.cardList.setOnClickListener {
            val intent = Intent(it.context, DetailInternasionalActivity::class.java)
            intent.putExtra("kotaInt", listInternasional[position].kotaInt)
            intent.putExtra("jadwalInt", listInternasional[position].jadwalInt)
            intent.putExtra("hargaInt", listInternasional[position].hargaInt)
            intent.putExtra("imageInt", listInternasional[position].gambarInt)
            intent.putExtra("availableInt", listInternasional[position].availableInt)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listInternasional.size
    }
}
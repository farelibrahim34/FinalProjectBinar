package com.finpro.garudanih.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finpro.garudanih.databinding.ItemBinding
import com.finpro.garudanih.model.Ticket


class AdapterTiket (private val listTiket : List<Ticket>): RecyclerView.Adapter<AdapterTiket.ViewHolder>(){
    class ViewHolder (val binding : ItemBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtKota.text = listTiket[position].destinationCode
        holder.binding.txtJadwal.text = listTiket[position].takeOff
        holder.binding.txtHarga.text = listTiket[position].price.toString()
//        holder.binding.ivListpesawat.setImageResource(listPesawat[position].gambar)
        holder.binding.txtAvailable.text = listTiket[position].totalChair.toString()
        holder.binding.txtClass.text = listTiket[position].classX
    }

    override fun getItemCount(): Int {
        return listTiket.size
    }
}
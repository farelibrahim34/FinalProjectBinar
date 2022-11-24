package com.finpro.garudanih.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finpro.garudanih.databinding.ItemBinding
import com.finpro.garudanih.model.ListPesawat

class AdapterListPesawat(val listPesawat : ArrayList<ListPesawat>): RecyclerView.Adapter<AdapterListPesawat.ViewHolder>() {
    class ViewHolder (var binding : ItemBinding):RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtKota.text = listPesawat[position].kota
        holder.binding.txtJadwal.text = listPesawat[position].jadwal
        holder.binding.txtHarga.text = listPesawat[position].harga.toString()
        holder.binding.txtAvailable.text = listPesawat[position].available

    }

    override fun getItemCount(): Int {
        return listPesawat.size
    }
}
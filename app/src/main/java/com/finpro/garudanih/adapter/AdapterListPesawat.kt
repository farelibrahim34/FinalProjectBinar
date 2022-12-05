package com.finpro.garudanih.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finpro.garudanih.databinding.ItemBinding
import com.finpro.garudanih.model.Ticket



class AdapterListPesawat(val listPesawat : List<Ticket>): RecyclerView.Adapter<AdapterListPesawat.ViewHolder>() {
    class ViewHolder (var binding : ItemBinding):RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.binding.txtKotaTujuan.text = listPesawat[position].destinationCode
        holder.binding.txtJadwal.text = listPesawat[position].takeOff
        holder.binding.txtHarga.text = listPesawat[position].price.toString()
    }


    override fun getItemCount(): Int {
        return listPesawat.size
    }
}
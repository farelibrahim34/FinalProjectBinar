package com.finpro.garudanih.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finpro.garudanih.databinding.ItemBinding
import com.finpro.garudanih.databinding.ItemShowAllBinding
import com.finpro.garudanih.model.Ticket



class AdapterLokal(val listPesawat : List<Ticket>): RecyclerView.Adapter<AdapterLokal.ViewHolder>() {
    class ViewHolder (var binding : ItemShowAllBinding):RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemShowAllBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.binding.txtKotaTujuan.text = listPesawat[position].destinationCode
        holder.binding.txtKotaAsal.text = listPesawat[position].departureCode
        holder.binding.txtJadwal.text = listPesawat[position].takeOff
        holder.binding.txtHarga.text = "Rp"+listPesawat[position].price.toString()
//        holder.binding.ivListpesawat.setImageResource(listTiket[position].type)
        holder.binding.txtAvailable.text = listPesawat[position].totalChair.toString()
        holder.binding.txtClass.text = listPesawat[position].classX
    }


    override fun getItemCount(): Int {
        return listPesawat.size
    }
}
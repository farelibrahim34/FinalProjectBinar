package com.finpro.garudanih.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finpro.garudanih.databinding.ItemOrderBinding
import com.finpro.garudanih.model.ListPesawat

class AdapterOrder (val listOrder: ArrayList<ListPesawat>):RecyclerView.Adapter<AdapterOrder.ViewHolder>(){
    class ViewHolder(var binding : ItemOrderBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemOrderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtKotaOrder.text = listOrder[position].kota
        holder.binding.txtHargaOrder.text = listOrder[position].harga.toString()
        holder.binding.txtTglOrder.text = listOrder[position].jadwal
        holder.binding.txtStatusOrder.text = listOrder[position].status
    }

    override fun getItemCount(): Int {
        return listOrder.size
    }
}
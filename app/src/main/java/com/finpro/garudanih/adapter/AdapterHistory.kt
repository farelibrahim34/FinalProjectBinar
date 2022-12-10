package com.finpro.garudanih.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finpro.garudanih.databinding.ItemOrderBinding
import com.finpro.garudanih.model.Transaction

class AdapterHistory(val listHistory: List<Transaction>?):RecyclerView.Adapter<AdapterHistory.ViewHolder>(){
    class ViewHolder(var binding : ItemOrderBinding): RecyclerView.ViewHolder(binding.root) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemOrderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtDestination.text = listHistory!![position].ticket!!.destinationCode
        holder.binding.txtDeparture.text = listHistory[position].ticket.departureCode
        holder.binding.txtClassHistory.text = listHistory[position].ticket.classX+" Class"
        holder.binding.txtOrderBy.text = "Order By : "+listHistory[position].orderBy
        holder.binding.txtTglOrder.text = listHistory[position].createdAt
        holder.binding.txtIsPaid.text = listHistory[position].isPaid.toString()
        if (listHistory[position].isPaid == false){
            holder.binding.txtIsPaid.text = "Belum Bayar"
        }else{
            holder.binding.txtIsPaid.text = "Sudah Bayar"
        }
        holder.binding.txtCodeTiket.text = "Code Ticket : "+listHistory[position].ticketCode
    }
    override fun getItemCount(): Int {
        return listHistory?.size ?: 0
    }
}
package com.finpro.garudanih.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finpro.garudanih.databinding.ItemOrderBinding
import com.finpro.garudanih.model.Transaction
import com.finpro.garudanih.view.detils.DetailPesawatActivity
import com.finpro.garudanih.view.tiketorder.TiketOrderActivity
import com.finpro.garudanih.view.transaksi.TransaksiActivity

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
        }else if (listHistory[position].isPaid == true){
            holder.binding.txtIsPaid.text = "Sudah Bayar"
        }
        holder.binding.txtCodeTiket.text = "Code Ticket : "+listHistory[position].ticketCode

        holder.binding.cardView3.setOnClickListener {
            val intent = Intent(it.context, TiketOrderActivity::class.java)
            intent.putExtra("destination",listHistory[position].ticket.destination)
            intent.putExtra("departure",listHistory[position].ticket.departure)
            intent.putExtra("idtrans",listHistory[position].id)
            intent.putExtra("harga",listHistory[position].ticket.price)
            intent.putExtra("paid",listHistory[position].isPaid)
            intent.putExtra("orderby",listHistory[position].orderBy)
            intent.putExtra("ktp",listHistory[position].ktp)
            intent.putExtra("kursi",listHistory[position].numChair)
            intent.putExtra("desticode",listHistory[position].ticket.destinationCode)
            intent.putExtra("deparcode",listHistory[position].ticket.departureCode)
            intent.putExtra("class",listHistory[position].ticket.classX)
            intent.putExtra("jadwal",listHistory[position].ticket.takeOff)
            intent.putExtra("nopenerbangan",listHistory[position].ticketCode)
            it.context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return listHistory?.size ?: 0
    }
}
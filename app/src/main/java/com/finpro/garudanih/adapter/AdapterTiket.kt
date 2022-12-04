package com.finpro.garudanih.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finpro.garudanih.databinding.ItemBinding
import com.finpro.garudanih.model.Ticket
import com.finpro.garudanih.view.detils.DetailInternasionalActivity
import com.finpro.garudanih.view.detils.DetailPesawatActivity

class AdapterTiket (private val listTiket : List<Ticket>): RecyclerView.Adapter<AdapterTiket.ViewHolder>(){
    class ViewHolder (val binding : ItemBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtKotaTujuan.text = listTiket[position].destinationCode
        holder.binding.txtKotaAsal.text = listTiket[position].departureCode
        holder.binding.txtJadwal.text = listTiket[position].takeOff
        holder.binding.txtHarga.text = listTiket[position].price.toString()
//        holder.binding.ivListpesawat.setImageResource(listPesawat[position].gambar)
        holder.binding.txtAvailable.text = listTiket[position].totalChair.toString()
        holder.binding.txtClass.text = listTiket[position].classX

        holder.binding.cardList.setOnClickListener {
            val detail = Intent(it.context, DetailPesawatActivity::class.java)
            detail.putExtra("tiket",listTiket[position].id)
            it.context.startActivity(detail)

//            val intent = Intent(it.context, DetailInternasionalActivity::class.java)
//            intent.putExtra("kota", listTiket[position].destinationCode)
//            intent.putExtra("jadwalInt", listTiket[position].takeOff)
//            intent.putExtra("hargaInt", listTiket[position].price)
////            intent.putExtra("imageInt", listTiket[position].gambarInt)
//            intent.putExtra("availableInt", listTiket[position].totalChair)
//            it.context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {
        return listTiket.size
    }
}
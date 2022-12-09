package com.finpro.garudanih.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finpro.garudanih.databinding.ItemBinding
import com.finpro.garudanih.databinding.ItemInternasionalBinding
import com.finpro.garudanih.model.ListInternasional
import com.finpro.garudanih.model.ListPesawat
import com.finpro.garudanih.model.ResponseListTiket
import com.finpro.garudanih.model.Ticket
import com.finpro.garudanih.view.detils.DetailInternasionalActivity
import com.finpro.garudanih.view.detils.DetailPesawatActivity

class AdapterInternasional(val listInternasional : List<Ticket>): RecyclerView.Adapter<AdapterInternasional.ViewHolder>() {

    class ViewHolder (var binding : ItemBinding):RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtKotaTujuan.text = listInternasional[position].destinationCode
        holder.binding.txtKotaAsal.text = listInternasional[position].departureCode
        holder.binding.txtJadwal.text = listInternasional[position].takeOff
        holder.binding.txtHarga.text = "Rp"+listInternasional[position].price.toString()
//        holder.binding.ivListpesawat.setImageResource(listTiket[position].type)
        holder.binding.txtAvailable.text = listInternasional[position].totalChair.toString()
        holder.binding.txtClass.text = listInternasional[position].classX
        holder.binding.cardList.setOnClickListener {
            val intent = Intent(it.context, DetailPesawatActivity::class.java)
            intent.putExtra("id", listInternasional[position].id)
            intent.putExtra("destinasi", listInternasional[position].destination)
            intent.putExtra("departure", listInternasional[position].departure)
            intent.putExtra("jadwal", listInternasional[position].takeOff)
            intent.putExtra("harga", listInternasional[position].price)
            intent.putExtra("totalchair", listInternasional[position].totalChair)
            intent.putExtra("class", listInternasional[position].classX)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listInternasional.size
    }
}
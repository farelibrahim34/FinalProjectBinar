package com.finpro.garudanih.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.finpro.garudanih.databinding.ItemShowAllBinding
import com.finpro.garudanih.model.responsedetail.Data
import com.finpro.garudanih.model.responsedetail.ReturnTicket
import com.finpro.garudanih.model.responsedetail.Ticket
import com.finpro.garudanih.view.detils.DetailPesawatActivity
import com.finpro.garudanih.view.detils.DetailPulangActivity
import com.finpro.garudanih.view.pemesanan.PemesananActivity
import com.finpro.garudanih.viewmodel.AuthViewModel
import com.finpro.garudanih.viewmodel.ViewModelUser

class AdapterPP(var listTiketPP: List<ReturnTicket>):RecyclerView.Adapter<AdapterPP.ViewHolder>() {
    lateinit var getID : Ticket
    lateinit var viewModelUser: ViewModelUser
    lateinit var authViewModel: AuthViewModel
    private var tokenUser: String = ""
    lateinit var tiketAdapter: AdapterTiket

    class ViewHolder(val binding: ItemShowAllBinding) : RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemShowAllBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtKotaTujuan.text = listTiketPP[position].destinationCode
        holder.binding.txtKotaAsal.text = listTiketPP[position].departureCode
        holder.binding.txtJadwal.text = listTiketPP[position].takeOff
        holder.binding.txtHarga.text = "Rp" + listTiketPP[position].price.toString()
//        holder.binding.ivListpesawat.setImageResource(listTiket[position].type)
        holder.binding.txtAvailable.text = listTiketPP[position].totalChair.toString()
        holder.binding.txtClass.text = listTiketPP[position].classX


        holder.binding.cardList.setOnClickListener {


            val intent = Intent(it.context, DetailPulangActivity::class.java)

            intent.putExtra("id", listTiketPP[position].id)
            intent.putExtra("destinasi", listTiketPP[position].destination)
            intent.putExtra("departure", listTiketPP[position].departure)
            intent.putExtra("jadwal", listTiketPP[position].takeOff)
            intent.putExtra("harga", listTiketPP[position].price)
            intent.putExtra("totalchair", listTiketPP[position].totalChair)
            intent.putExtra("class", listTiketPP[position].classX)

            it.context.startActivity(intent)
        }
    }



    override fun getItemCount(): Int {
        return listTiketPP.size
    }
}
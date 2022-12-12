package com.finpro.garudanih.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finpro.garudanih.databinding.ItemBinding
import com.finpro.garudanih.wishlist.DataWishPesawatLoc
import com.finpro.garudanih.wishlistinternasional.dataWishPesawatInternasional

class AdapterWishListInternasional(val Wishlistinternasional : List<dataWishPesawatInternasional>): RecyclerView.Adapter<AdapterWishListInternasional.ViewHolder>()  {
    class ViewHolder (val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtKotaTujuan.text = Wishlistinternasional[position].destination
        holder.binding.txtKotaAsal.text = Wishlistinternasional[position].departure
        holder.binding.txtJadwal.text = Wishlistinternasional[position].takeOff
        holder.binding.txtHarga.text = "Rp"+Wishlistinternasional[position].price.toString()
//        holder.binding.ivListpesawat.setImageResource(listTiket[position].type)
        holder.binding.txtAvailable.text = Wishlistinternasional[position].totalChair.toString()
        holder.binding.txtClass.text = Wishlistinternasional[position].classX
    }

    override fun getItemCount(): Int {
        return  Wishlistinternasional.size
    }
}
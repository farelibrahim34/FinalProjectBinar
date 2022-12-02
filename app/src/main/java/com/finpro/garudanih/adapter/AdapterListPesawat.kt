package com.finpro.garudanih.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finpro.garudanih.databinding.ItemBinding
import com.finpro.garudanih.model.ListPesawat
import com.finpro.garudanih.view.detils.DetailPesawatActivity

//class AdapterListPesawat(val listPesawat : ArrayList<ListPesawat>): RecyclerView.Adapter<AdapterListPesawat.ViewHolder>() {
//
//    class ViewHolder (var binding : ItemBinding):RecyclerView.ViewHolder(binding.root){
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//
//        holder.binding.txtKota.text = listPesawat[position].kota
//        holder.binding.txtJadwal.text = listPesawat[position].jadwal
//        holder.binding.txtHarga.text = listPesawat[position].harga.toString()
//        holder.binding.ivListpesawat.setImageResource(listPesawat[position].gambar)
//        holder.binding.txtAvailable.text = listPesawat[position].availableholder.binding.txtKota.text = listPesawat[position].destinationCode
//        holder.binding.txtJadwal.text = listPesawat[position].takeOff
//        holder.binding.txtHarga.text = listPesawat[position].price.toString()
////        holder.binding.ivListpesawat.setImageResource(listPesawat[position].gambar)
//        holder.binding.txtAvailable.text = listPesawat[position].totalChair.toString()
//        holder.binding.txtClass.text = listPesawat[position].classX
//
//
//        holder.binding.cardList.setOnClickListener {
//            val intent = Intent(it.context, DetailPesawatActivity::class.java)
//            intent.putExtra("kota", listPesawat[position].destinationCode)
//            intent.putExtra("jadwal", listPesawat[position].takeOff)
//            intent.putExtra("harga", listPesawat[position].price)
////            intent.putExtra("image", listPesawat[position].gambar)
//            intent.putExtra("available", listPesawat[position].totalChair)
//            intent.putExtra("kelas", listPesawat[position].classX)
//            it.context.startActivity(intent)
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return listPesawat.size
//    }
//}
package com.finpro.garudanih.adapter

import android.content.Intent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.finpro.garudanih.databinding.ItemBinding
import com.finpro.garudanih.model.Ticket
import com.finpro.garudanih.view.detils.DetailPesawatActivity

@Suppress("RemoveEmptyClassBody", "LiftReturnOrAssignment")
class AdapterTiket (private var onClick : (Ticket)->Unit): RecyclerView.Adapter<AdapterTiket.ViewHolder>() {
    private var listTiket : List<Ticket>? = null
    fun setListTiket(listTiketLocal : List<Ticket>){
        this.listTiket = listTiketLocal
    }
    class ViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtKotaTujuan.text = listTiket!![position].destinationCode
        holder.binding.txtKotaAsal.text = listTiket!![position].departureCode
        holder.binding.txtJadwal.text = listTiket!![position].takeOff
        holder.binding.txtHarga.text = "Rp"+listTiket!![position].price.toString()
//        holder.binding.ivListpesawat.setImageResource(listTiket[position].type)
        holder.binding.txtAvailable.text = listTiket!![position].totalChair.toString()
        holder.binding.txtClass.text = listTiket!![position].classX
        holder.binding.cardList.setOnClickListener {

            val intent = Intent(it.context, DetailPesawatActivity::class.java)
            intent.putExtra("id", listTiket!![position].id)
            intent.putExtra("destinasi", listTiket!![position].destination)
            intent.putExtra("departure", listTiket!![position].departure)
            intent.putExtra("jadwal", listTiket!![position].takeOff)
            intent.putExtra("harga", listTiket!![position].price)
            intent.putExtra("totalchair", listTiket!![position].totalChair)
            intent.putExtra("class", listTiket!![position].classX)

            it.context.startActivity(intent)
        }
        holder.binding.cardList.setOnClickListener {
            onClick(listTiket!![position])
        }
    }


    override fun getItemCount(): Int {
        if (listTiket == null){
            return 0
        }else{
            return listTiket!!.size
        }
    }

}
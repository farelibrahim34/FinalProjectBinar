package com.finpro.garudanih.adapter

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.finpro.garudanih.R
import com.finpro.garudanih.databinding.ItemWishlistBinding
import com.finpro.garudanih.wishlist.fragment.InternatonalTablayoutFragment
import com.finpro.garudanih.wishlistinternasional.DatabaseWishPesawatInternasional
import com.finpro.garudanih.wishlistinternasional.dataWishPesawatInternasional
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AdapterWishListInternasional(val Wishlistinternasional : List<dataWishPesawatInternasional>): RecyclerView.Adapter<AdapterWishListInternasional.ViewHolder>()  {
    private lateinit var context : Context
    var databaseWishPesawatInternasional : DatabaseWishPesawatInternasional? = null

    inner class ViewHolder (val binding: ItemWishlistBinding) : RecyclerView.ViewHolder(binding.root){
        private lateinit var listener : OnAdapterListener
        @SuppressLint("SuspiciousIndentation")
        fun bind(wishlist: dataWishPesawatInternasional) {
            binding.mainCard.setOnClickListener{
                val dialog = Dialog(context)
                dialog.setContentView(R.layout.custom_dialog_delete)
                dialog.getWindow()!!.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                val btnDeleteYes : Button = dialog.findViewById(R.id.btnDeleteYes)
                val btnDeleteNo : Button = dialog.findViewById(R.id.btnDeleteNo)

                btnDeleteYes.setOnClickListener(){
                    Toast.makeText(context, "Yes Clicked , data", Toast.LENGTH_SHORT).show()
                    listener.onDelete(wishlist = dataWishPesawatInternasional(0,"","","",0,0,""))
                    dialog.dismiss()
                }
                btnDeleteNo.setOnClickListener(){
                    Toast.makeText(context, "No Clicked", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                dialog.show()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemWishlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(Wishlistinternasional[position])
        holder.binding.txtKotaTujuan.text = Wishlistinternasional[position].destination
        holder.binding.txtKotaAsal.text = Wishlistinternasional[position].departure
        holder.binding.txtJadwal.text = Wishlistinternasional[position].takeOff
        holder.binding.txtHarga.text = "Rp"+Wishlistinternasional[position].price.toString()
//        holder.binding.ivListpesawat.setImageResource(listTiket[position].type)
        holder.binding.txtAvailable.text = Wishlistinternasional[position].totalChair.toString()
        holder.binding.txtClass.text = Wishlistinternasional[position].classX
        holder.binding.delete.setOnClickListener {
            databaseWishPesawatInternasional = DatabaseWishPesawatInternasional.getInstance(it.context)
            GlobalScope.async {
                val delete =
                    databaseWishPesawatInternasional?.WishInternasionalDao()?.deleteWishInter(Wishlistinternasional[position])
                (holder.itemView.context as InternatonalTablayoutFragment).run {
                    Toast.makeText(it.context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT)
                        .show()
                    (holder.itemView.context as InternatonalTablayoutFragment).context.apply {
                        databaseWishPesawatInternasional
                    }
                }
            }
            Toast.makeText(it.context, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return  Wishlistinternasional.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }
    interface OnAdapterListener {
        fun onDelete(wishlist: dataWishPesawatInternasional)
    }
}
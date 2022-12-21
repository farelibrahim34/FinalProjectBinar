package com.finpro.garudanih.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.finpro.garudanih.databinding.ItemWishlistBinding
import com.finpro.garudanih.wishlist.fragment.InternatonalTablayoutFragment
import com.finpro.garudanih.wishlistinternasional.DatabaseWishPesawatInternasional
import com.finpro.garudanih.wishlistinternasional.WishpesawatDaoInternasional
import com.finpro.garudanih.wishlistinternasional.dataWishPesawatInternasional
import kotlinx.coroutines.*

class AdapterWishListInternasional(): RecyclerView.Adapter<AdapterWishListInternasional.ViewHolder>()  {

    private lateinit var context : Context
    var databaseWishPesawatInternasional : DatabaseWishPesawatInternasional? = null
    private lateinit var daoInternational : WishpesawatDaoInternasional
    private var Wishlistinternasional : List<dataWishPesawatInternasional> = emptyList()
    private var database: DatabaseWishPesawatInternasional? = null

    inner class ViewHolder (val binding: ItemWishlistBinding) : RecyclerView.ViewHolder(binding.root){
        private lateinit var listener : OnAdapterListener
//        @SuppressLint("SuspiciousIndentation")
//        fun bind(wishlist: dataWishPesawatInternasional) {
//            binding.mainCard.setOnClickListener{
//                val dialog = Dialog(context)
//                dialog.setContentView(R.layout.custom_dialog_delete)
//                dialog.getWindow()!!.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//
//                val btnDeleteYes : Button = dialog.findViewById(R.id.btnDeleteYes)
//                val btnDeleteNo : Button = dialog.findViewById(R.id.btnDeleteNo)
//
//                btnDeleteYes.setOnClickListener(){
//                    Toast.makeText(context, "Yes Clicked , data", Toast.LENGTH_SHORT).show()
//                    listener.onDelete(wishlist = dataWishPesawatInternasional(0,"","","",0,0,""))
//                    dialog.dismiss()
//                }
//                btnDeleteNo.setOnClickListener(){
//                    Toast.makeText(context, "No Clicked", Toast.LENGTH_SHORT).show()
//                    dialog.dismiss()
//                }
//                dialog.show()
//            }
//
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemWishlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        database = DatabaseWishPesawatInternasional.getInstance(parent.context)
        if(database != null){
            daoInternational = database!!.WishInternasionalDao()
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(Wishlistinternasional[position])
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

    fun setListWishlist(list: List<dataWishPesawatInternasional>){
        this.Wishlistinternasional = list
        notifyDataSetChanged()
    }

    fun getWishlist(position: Int): dataWishPesawatInternasional = Wishlistinternasional[position]

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

    fun deleteInternational(wishInternational: dataWishPesawatInternasional, position: Int){
        CoroutineScope(Dispatchers.IO).launch {
            daoInternational.deleteWishInter(wishInternational)
        }
        notifyItemChanged(position)
    }
}
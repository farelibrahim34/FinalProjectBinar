package com.finpro.garudanih.view.detils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.finpro.garudanih.R
import com.finpro.garudanih.databinding.ActivityDetailInternasionalBinding
import com.finpro.garudanih.model.Ticket
import com.finpro.garudanih.view.HomeBottomActivity
import com.finpro.garudanih.wishlist.DataWishPesawatLoc
import com.finpro.garudanih.wishlist.DatabaseWishPesawatLoc
import com.finpro.garudanih.wishlist.WishPesawatDaoLoc
import com.finpro.garudanih.wishlistinternasional.DatabaseWishPesawatInternasional
import com.finpro.garudanih.wishlistinternasional.WishpesawatDaoInternasional
import com.finpro.garudanih.wishlistinternasional.dataWishPesawatInternasional
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@AndroidEntryPoint
class DetailInternasionalActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailInternasionalBinding
    private var wishpesawatDaoInternasional : WishpesawatDaoInternasional? =null
    private var databaseWishPesawatInternasional : DatabaseWishPesawatInternasional? = null
    private var id :Int?=null

    companion object{
        const val  EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailInternasionalBinding.inflate(layoutInflater)
        setContentView(binding.root)


        databaseWishPesawatInternasional = DatabaseWishPesawatInternasional.getInstance(this)
        wishpesawatDaoInternasional = databaseWishPesawatInternasional?.WishInternasionalDao()
        id = intent.getIntExtra(EXTRA_ID, 0)

        binding.ivBackDetail.setOnClickListener {
            startActivity(Intent(this, HomeBottomActivity::class.java))
        }
        getListInternasional()

        val detail = intent.getSerializableExtra("detail") as Ticket
        binding.txtHargaDetail.text = "Harga Tiket \nRp"+detail.price
        binding.txtAsal.text = detail.departure
        binding.txtDeskripsi.text = "Available "+detail.totalChair
        binding.ivKota.setImageResource(R.drawable.pesawat)

        binding.wishlist.setOnClickListener{
            GlobalScope.async {
                val d = intent.getSerializableExtra("detail") as Ticket
                val idd = detail.id.toInt()
                val asal = detail.destinationCode
                val tujuan = detail.departureCode
                val jad = detail.takeOff
                val har = detail.price
                val bangku = detail.totalChair
                val clas = detail.classX
                val wishList = databaseWishPesawatInternasional?.WishInternasionalDao()?.addToWishListInternasional(
                    dataWishPesawatInternasional(idd,asal,tujuan,jad,har,bangku,clas)
                )

                runOnUiThread {
                    if (wishList != 0.toLong()){
                        Toast.makeText(this@DetailInternasionalActivity, "Berhasil Menambah Ke WishList", Toast.LENGTH_SHORT).show()
                        var _isChecked = false
                        _isChecked = !_isChecked
                        binding.wishlist.isChecked = _isChecked

                    }else{
                        Toast.makeText(this@DetailInternasionalActivity, "Gagal Menambah Ke Wishlist", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


    fun getListInternasional(){

        val itemListInternasional = intent.extras
        val jadwalInt = itemListInternasional?.getString("jadwalInt")
        val hargaInt = itemListInternasional?.getString("hargaInt")
        val imageInt = itemListInternasional?.getInt("imageInt",0)
        val availableInt = itemListInternasional?.getString("availableInt")

//        binding.txtAsal.text = kotaInt
        binding.txtHargaDetail.text = hargaInt
        if (imageInt != null) {
            binding.ivKota.setImageResource(imageInt)
        }
        binding.txtDeskripsi.text = availableInt

    }
}
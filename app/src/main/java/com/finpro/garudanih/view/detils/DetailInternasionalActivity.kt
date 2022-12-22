@file:Suppress("RemoveRedundantCallsOfConversionMethods")

package com.finpro.garudanih.view.detils

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.finpro.garudanih.R
import com.finpro.garudanih.databinding.ActivityDetailInternasionalBinding
import com.finpro.garudanih.model.Ticket
import com.finpro.garudanih.view.HomeBottomActivity
import com.finpro.garudanih.view.pemesanan.PemesananActivity
import com.finpro.garudanih.wishlistinternasional.DatabaseWishPesawatInternasional
import com.finpro.garudanih.wishlistinternasional.WishpesawatDaoInternasional
import com.finpro.garudanih.wishlistinternasional.dataWishPesawatInternasional
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@Suppress("DeferredResultUnused", "LocalVariableName", "MemberVisibilityCanBePrivate")
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

    }


    fun getListInternasional(){

        val itemListInternasional = intent.extras
        val jadwalInt = itemListInternasional?.getString("jadwalInt")
        val hargaInt = itemListInternasional?.getString("hargaInt")
        val imageInt = itemListInternasional?.getInt("imageInt",0)
        val availableInt = itemListInternasional?.getString("availableInt")

        val detail = intent.getSerializableExtra("detail") as Ticket
        binding.idTIket.text = detail.id.toString()
        binding.txtInputAsal.text = detail.departure
        binding.txtInputTujuan.text = detail.destination
        binding.txtHargaDetail.text = "Harga Tiket \nRp"+detail.price
        binding.txtJadwal.text = "Jadwal : \n"+detail.takeOff
        binding.txtChair.text =  "Available "+detail.totalChair
        binding.txtClass.text = detail.classX+" Class"
        binding.ivKota.setImageResource(R.drawable.pesawat)

        binding.btnOrder.setOnClickListener {
            val intent = Intent(this, PemesananActivity::class.java)
            intent.putExtra("id",id)
            intent.putExtra("harga",detail.price)
            intent.putExtra("destinasi",detail.destination)
            intent.putExtra("departure",detail.departure)
            intent.putExtra("jadwal",detail.takeOff)
            startActivity(intent)
        }

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
}
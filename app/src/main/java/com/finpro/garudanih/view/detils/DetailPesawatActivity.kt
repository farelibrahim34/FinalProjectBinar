@file:Suppress("ConvertToStringTemplate")

package com.finpro.garudanih.view.detils

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.finpro.garudanih.databinding.ActivityDetailPesawatBinding
import com.finpro.garudanih.model.Ticket
import com.finpro.garudanih.view.HomeBottomActivity
import com.finpro.garudanih.view.pemesanan.PemesananActivity
import com.finpro.garudanih.view.tiketpulang.TiketPulangActivity
import com.finpro.garudanih.viewmodel.TiketViewModel
import com.finpro.garudanih.wishlist.DataWishPesawatLoc
import com.finpro.garudanih.wishlist.DatabaseWishPesawatLoc
import com.finpro.garudanih.wishlist.WishPesawatDaoLoc
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@Suppress("DeferredResultUnused", "LocalVariableName", "MemberVisibilityCanBePrivate",
    "ReplaceGetOrSet", "ConvertToStringTemplate", "unused"
)
@AndroidEntryPoint
class DetailPesawatActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailPesawatBinding
    lateinit var viewModelTiket : TiketViewModel
    private var wishPesawatDaoLoc : WishPesawatDaoLoc? =null
    private var databaseWishPesawatLoc : DatabaseWishPesawatLoc? = null
    private var idd :Int?=null
    companion object{
        const val  EXTRA_ID = "extra_id"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPesawatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseWishPesawatLoc = DatabaseWishPesawatLoc.getInstance(this)
        wishPesawatDaoLoc = databaseWishPesawatLoc?.WishDao()
        idd = intent.getIntExtra(EXTRA_ID, 0)

        viewModelTiket = ViewModelProvider(this).get(TiketViewModel::class.java)
        val id = intent.getIntExtra("id",0)
        val harga = intent.getIntExtra("harga",0)
        val kota = intent.getStringExtra("destinasi")
        val keberangkatan = intent.getStringExtra("departure")
        val jadwal = intent.getStringExtra("jadwal")

        binding.btnOrder.setOnClickListener {
            val intent = Intent(this, PemesananActivity::class.java)
            intent.putExtra("id",idd)
            intent.putExtra("harga",harga)
            intent.putExtra("destinasi",kota)
            intent.putExtra("departure",keberangkatan)
            intent.putExtra("jadwal",jadwal)
            startActivity(intent)

        }

        val detail = intent.getSerializableExtra("lokal") as Ticket
        binding.idTIket2.text = "ID Tiket Pergi : "+detail.id.toString()
        binding.txtInputAsal.text = detail.departure
        binding.txtInputTujuan.text = detail.destination
        binding.txtHargaDetail.text = "Harga Tiket \nRp"+detail.price
        binding.txtJadwal.text = "Jadwal : \n"+detail.takeOff
        binding.txtChair.text =  "Available "+detail.totalChair
        binding.txtClass.text = detail.classX+" Class"



        binding.wishlist.setOnClickListener{
            GlobalScope.async {
                val d = intent.getSerializableExtra("lokal") as Ticket
                val idd = detail.id
                val asal = detail.destinationCode
                val tujuan = detail.departureCode
                val jad = detail.takeOff
                val har = detail.price
                val bangku = detail.totalChair
                val clas = detail.classX
                val wishList = databaseWishPesawatLoc?.WishDao()?.addToWishList(DataWishPesawatLoc(idd,asal,tujuan,jad,har,bangku,clas))

                runOnUiThread {
                    if (wishList != 0.toLong()){
                        Toast.makeText(this@DetailPesawatActivity, "Berhasil Menambah Ke WishList", Toast.LENGTH_SHORT).show()
                        var _isChecked = false
                        _isChecked = !_isChecked
                        binding.wishlist.isChecked = _isChecked

                    }else{
                        Toast.makeText(this@DetailPesawatActivity, "Gagal Menambah Ke Wishlist", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }


        binding.ivBackDetail.setOnClickListener {
            startActivity(Intent(this, HomeBottomActivity::class.java))
        }
        binding.btnPulang.setOnClickListener {
            val intent = Intent(this, TiketPulangActivity::class.java)
            intent.putExtra("id",idd)
            startActivity(intent)
        }

    }

    @SuppressLint("SetTextI18n")
    fun getListPesawat(){
        val itemListPesawat = intent

        val kota = itemListPesawat.getStringExtra("destinasi")
        val keberangkatan = itemListPesawat.getStringExtra("departure")
        val jadwal = itemListPesawat.getStringExtra("jadwal")
        val harga = itemListPesawat.getIntExtra("harga",0)
        val chair = itemListPesawat.getIntExtra("totalchair",0)
        val status = itemListPesawat.getStringExtra("class")

        binding.txtInputAsal.text = keberangkatan
        binding.txtInputTujuan.text = kota
        binding.txtHargaDetail.text = "Harga Tiket \nRp"+harga.toString()
        binding.txtJadwal.text = "Jadwal : \n"+jadwal
        binding.txtChair.text =  "Available "+chair.toString()
        binding.txtClass.text = status+" Class"

    }

}
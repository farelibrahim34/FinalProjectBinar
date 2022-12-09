package com.finpro.garudanih.view.detils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.finpro.garudanih.databinding.ActivityDetailPesawatBinding
import com.finpro.garudanih.model.Ticket
import com.finpro.garudanih.view.HomeBottomActivity
import com.finpro.garudanih.view.pemesanan.PemesananActivity
import com.finpro.garudanih.viewmodel.TiketViewModel
import com.finpro.garudanih.wishlist.DataWishPesawatLoc
import com.finpro.garudanih.wishlist.DatabaseWishPesawatLoc
import com.finpro.garudanih.wishlist.WishPesawatDaoLoc
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlin.properties.Delegates

@AndroidEntryPoint
class DetailPesawatActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailPesawatBinding
    lateinit var viewModelTiket : TiketViewModel
    private var wishPesawatDaoLoc : WishPesawatDaoLoc? =null
    private var databaseWishPesawatLoc : DatabaseWishPesawatLoc? = null
    private var id :Int?=null

    companion object{
        const val  EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPesawatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseWishPesawatLoc = DatabaseWishPesawatLoc.getInstance(this)
        wishPesawatDaoLoc = databaseWishPesawatLoc?.WishDao()
        id = intent.getIntExtra(EXTRA_ID, 0)

        viewModelTiket = ViewModelProvider(this).get(TiketViewModel::class.java)
        val id = intent.getIntExtra("id",0)

        binding.btnOrder.setOnClickListener {
            val intent = Intent(this, PemesananActivity::class.java)
            intent.putExtra("id",id)
            startActivity(intent)
        }


        binding.ivBackDetail.setOnClickListener {
            startActivity(Intent(this, TiketLokalActivity::class.java))
        }
        getListPesawat()


    }

    fun getListPesawat(){
        val itemListPesawat = intent

        val kota = itemListPesawat.getStringExtra("destinasi")
        val keberangkatan = itemListPesawat.getStringExtra("departure")
        val jadwal = itemListPesawat.getStringExtra("jadwal")
        val harga = itemListPesawat.getIntExtra("harga",0)
        val chair = itemListPesawat.getIntExtra("totalchair",0)
        val status = itemListPesawat.getStringExtra("class")
//        val image = itemListPesawat.getIntExtra("image",0)

        binding.txtInputAsal.text = keberangkatan
        binding.txtInputTujuan.text = kota
        binding.txtHargaDetail.text = "Harga Tiket \nRp"+harga.toString()
        binding.txtJadwal.text = "Jadwal : \n"+jadwal
        binding.txtChair.text =  "Available "+chair.toString()
        binding.txtClass.text = status+" Class"
//        binding.ivKota.setImageResource(image)


        binding.wishlist.setOnClickListener{
            GlobalScope.async {
                val d = intent.getSerializableExtra("detailnews") as Ticket
                val idd = d.id
                val asal = d.destination
                val tujuan = d.departure
                val jad = d.takeOff
                val har = d.price
                val bangku = d.totalChair
                val clas = d.classX
                val wishList = databaseWishPesawatLoc?.WishDao()?.addToWishList(DataWishPesawatLoc(idd,asal,tujuan,jad, har,bangku,clas))

                runOnUiThread {
                    if (wishList !=0.toLong()){
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
    }

}
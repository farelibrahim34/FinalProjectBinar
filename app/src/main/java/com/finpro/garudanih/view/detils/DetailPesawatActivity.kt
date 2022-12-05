package com.finpro.garudanih.view.detils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.finpro.garudanih.databinding.ActivityDetailPesawatBinding
import com.finpro.garudanih.view.HomeBottomActivity
import com.finpro.garudanih.viewmodel.TiketViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class DetailPesawatActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailPesawatBinding
    lateinit var viewModelTiket : TiketViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPesawatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModelTiket = ViewModelProvider(this).get(TiketViewModel::class.java)


        binding.ivBackDetail.setOnClickListener {
            startActivity(Intent(this, HomeBottomActivity::class.java))
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

        binding.txtAsal.text =          "Keberangkatan\n"+keberangkatan+"\n"
        binding.txtTujuan.text =        "Tujuan\n"+kota+"\n"
        binding.txtHargaDetail.text =   "Harga Tiket \nRp"+harga.toString()
        binding.txtJadwal.text =        "Jadwal Keberangkatan   : \n"+jadwal
        binding.txtChair.text =         "Available "+chair.toString()
        binding.txtClass.text = status+" Class"
//        binding.ivKota.setImageResource(image)

    }

}
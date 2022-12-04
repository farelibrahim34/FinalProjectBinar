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
    var id by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPesawatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModelTiket = ViewModelProvider(this).get(TiketViewModel::class.java)

        id = intent.getStringExtra("tiket")!!.toString().toInt()
        Log.d("DEBUG_ID",id.toString())
        setDetail()

        binding.ivBackDetail.setOnClickListener {
            startActivity(Intent(this, HomeBottomActivity::class.java))
        }
//        getListPesawat()
    }

    fun getListPesawat(){
        val itemListPesawat = intent

        val kota = itemListPesawat.getStringExtra("kota")
        val jadwal = itemListPesawat.getStringExtra("jadwal")
        val harga = itemListPesawat.getStringExtra("harga")
        val image = itemListPesawat.getIntExtra("image",0)
        val status = itemListPesawat.getStringExtra("available")

        binding.txtTujuan.text = kota
        binding.txtHargaDetail.text = harga
        binding.ivKota.setImageResource(image)
        binding.txtDeskripsi.text = status

    }
    fun setDetail(){
        viewModelTiket.callGetTiketById(id)
        viewModelTiket.getDetailTiket(id).observe(this){
            binding.txtTujuan.setText("Tujuan \t"+ it!!.data.destination)
            binding.txtAsal.setText("Keberangkatan \t"+ it!!.data.departure)


        }

    }

}
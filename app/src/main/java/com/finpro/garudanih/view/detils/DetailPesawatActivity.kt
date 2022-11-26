package com.finpro.garudanih.view.detils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.finpro.garudanih.databinding.ActivityDetailPesawatBinding
import com.finpro.garudanih.view.HomeBottomActivity

class DetailPesawatActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailPesawatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPesawatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBackDetail.setOnClickListener {
            startActivity(Intent(this, HomeBottomActivity::class.java))
        }

        getListInternasional()
        getListPesawat()
    }
    fun getListInternasional(){

        val itemListInternasional = intent

        val kotaInt = itemListInternasional.getStringExtra("kotaInt")
        val jadwalInt = itemListInternasional.getStringExtra("jadwalInt")
        val hargaInt = itemListInternasional.getStringExtra("hargaInt")
        val imageInt = itemListInternasional.getIntExtra("imageInt",0)
        val availableInt = itemListInternasional.getStringExtra("availableInt")

        binding.txtNamaKota.text = kotaInt
        binding.txtHargaDetail.text = hargaInt
        binding.ivKota.setImageResource(imageInt)
        binding.txtDeskripsi.text = availableInt

    }

    fun getListPesawat(){
        val itemListPesawat = intent

        val kota = itemListPesawat.getStringExtra("kota")
        val jadwal = itemListPesawat.getStringExtra("jadwal")
        val harga = itemListPesawat.getStringExtra("harga")
        val image = itemListPesawat.getIntExtra("image",0)
        val status = itemListPesawat.getStringExtra("available")

        binding.txtNamaKota.text = kota
        binding.txtHargaDetail.text = harga
        binding.ivKota.setImageResource(image)
        binding.txtDeskripsi.text = status

    }

}
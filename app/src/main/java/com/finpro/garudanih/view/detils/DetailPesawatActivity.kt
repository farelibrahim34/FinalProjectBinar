package com.finpro.garudanih.view.detils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.finpro.garudanih.databinding.ActivityDetailPesawatBinding
import com.finpro.garudanih.view.HomeBottomActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPesawatActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailPesawatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPesawatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBackDetail.setOnClickListener {
            startActivity(Intent(this, HomeBottomActivity::class.java))
        }

        getListPesawat()
    }

    fun getListPesawat(){
        val itemListPesawat = intent

        val kota = itemListPesawat.getStringExtra("destinasi")
        val jadwal = itemListPesawat.getStringExtra("jadwal")
        val harga = itemListPesawat.getStringExtra("harga")
        val chair = itemListPesawat.getStringExtra("totalchair")
        val status = itemListPesawat.getStringExtra("class")
//        val image = itemListPesawat.getIntExtra("image",0)

        binding.txtNamaKota.text = kota
        binding.txtHargaDetail.text = harga
        binding.txtJadwal.text = jadwal
        binding.txtChair.text = chair
        binding.txtDeskripsi.text = status
//        binding.ivKota.setImageResource(image)

    }

}
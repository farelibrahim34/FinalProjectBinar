@file:Suppress("ConvertToStringTemplate", "ConvertToStringTemplate", "ConvertToStringTemplate",
    "ConvertToStringTemplate", "ConvertToStringTemplate", "ConvertToStringTemplate",
    "ConvertToStringTemplate", "ConvertToStringTemplate", "ConvertToStringTemplate"
)

package com.finpro.garudanih.view.detils

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.finpro.garudanih.databinding.ActivityDetailPulangBinding
import com.finpro.garudanih.view.pemesanan.PemesananPPActivity
import com.finpro.garudanih.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPulangActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailPulangBinding
    lateinit var authViewModel : AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPulangBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val idTiketPergi = intent.getIntExtra("getId",0)
        binding.idTIket3.text = "ID Tiket Pergi : "+idTiketPergi
        val idTiketPulang = intent.getIntExtra("id",0)
        binding.idTIket2.text = "ID Tiket Pulang: "+idTiketPulang.toString()
        val asal = intent.getStringExtra("departure")
        binding.txtInputAsal.text = asal
        val tujuan = intent.getStringExtra("destinasi")
        binding.txtInputTujuan.text = tujuan
        val harga = intent.getIntExtra("harga",0)
        binding.txtHargaDetail.text = "Harga Tiket \nRp"+harga
        val jadwal = intent.getStringExtra("jadwal")
        binding.txtJadwal.text = "Jadwal : \n"+jadwal
        val kursi = intent.getIntExtra("totalchair",0)
        binding.txtChair.text =  "Available "+kursi
        val kelas = intent.getStringExtra("class")
        binding.txtClass.text = kelas+" Class"


        binding.btnOrder.setOnClickListener {
            val intent = Intent(this, PemesananPPActivity::class.java)
            intent.putExtra("id",idTiketPulang)
            intent.putExtra("getId",idTiketPergi)
            startActivity(intent)
        }
    }
}
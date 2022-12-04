package com.finpro.garudanih.view.detils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.finpro.garudanih.databinding.ActivityDetailInternasionalBinding
import com.finpro.garudanih.view.HomeBottomActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailInternasionalActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailInternasionalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailInternasionalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBackDetail.setOnClickListener {
            startActivity(Intent(this, HomeBottomActivity::class.java))
        }
        getListInternasional()
    }


    fun getListInternasional(){

        val itemListInternasional = intent

        val kotaInt = itemListInternasional.getStringExtra("kotaInt")
        val jadwalInt = itemListInternasional.getStringExtra("jadwalInt")
        val hargaInt = itemListInternasional.getStringExtra("hargaInt")
        val imageInt = itemListInternasional.getIntExtra("imageInt",0)
        val availableInt = itemListInternasional.getStringExtra("availableInt")

        binding.txtAsal.text = kotaInt
        binding.txtHargaDetail.text = hargaInt
        binding.ivKota.setImageResource(imageInt)
        binding.txtDeskripsi.text = availableInt

    }
}
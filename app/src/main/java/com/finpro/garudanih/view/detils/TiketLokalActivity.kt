package com.finpro.garudanih.view.detils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.finpro.garudanih.adapter.AdapterLokal
import com.finpro.garudanih.adapter.AdapterTiket
import com.finpro.garudanih.databinding.ActivityTiketLokalBinding
import com.finpro.garudanih.view.HomeBottomActivity
import com.finpro.garudanih.view.fragments.home.HomeFragment
import com.finpro.garudanih.viewmodel.TiketViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TiketLokalActivity : AppCompatActivity() {
    lateinit var binding : ActivityTiketLokalBinding
    lateinit var tiketLokalAdapter : AdapterLokal
    lateinit var viewModelListTiket : TiketViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTiketLokalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTiketLokal()
        binding.ivBack.setOnClickListener {
            startActivity(Intent(this, HomeBottomActivity::class.java))
        }
    }

    private fun setTiketLokal(){
        val viewModelListTiket = ViewModelProvider(this).get(TiketViewModel::class.java)
        viewModelListTiket.getLdTiket().observe(this) {
            if (it != null) {
                binding.progressBar.visibility = View.GONE
                binding.rvAllLocal.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                tiketLokalAdapter = AdapterLokal(it.data.tickets)
                binding.rvAllLocal.adapter = tiketLokalAdapter
                Toast.makeText(this, "Data Tampil", Toast.LENGTH_SHORT).show()
            } else {
                binding.progressBar.visibility = View.VISIBLE
                Toast.makeText(this, "Data Tidak Tampil", Toast.LENGTH_SHORT).show()
            }
        }
        viewModelListTiket.CallApiTiket()
    }
}
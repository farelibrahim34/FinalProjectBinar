package com.finpro.garudanih.view.detils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.finpro.garudanih.R
import com.finpro.garudanih.adapter.AdapterInternasional
import com.finpro.garudanih.databinding.ActivityTiketInternasionalBinding
import com.finpro.garudanih.view.HomeBottomActivity
import com.finpro.garudanih.viewmodel.TiketViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TiketInternasionalActivity : AppCompatActivity() {
    lateinit var binding : ActivityTiketInternasionalBinding
    lateinit var viewModel : TiketViewModel
    lateinit var adapterTiketIntr : AdapterInternasional
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTiketInternasionalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ivBack.setOnClickListener {
            startActivity(Intent(this, HomeBottomActivity::class.java))
        }
        setTiketInternasional()
    }
    fun setTiketInternasional(){
        viewModel = ViewModelProvider(this).get(TiketViewModel::class.java)
        viewModel.getLdTiketIntr().observe(this){
            binding.progressBar.visibility = View.VISIBLE
            if (it != null){
                binding.progressBar.visibility = View.GONE
                binding.rvAllInternational.layoutManager = GridLayoutManager(this,2)
                adapterTiketIntr.setListTiketInter(it.data.tickets)
                adapterTiketIntr.notifyDataSetChanged()
                binding.rvAllInternational.adapter = adapterTiketIntr
                Toast.makeText(this, "Data Tampil", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Data Tidak Tampil", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.callApiTiketIntr()

    }
}
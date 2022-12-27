package com.finpro.garudanih.view.detils

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.finpro.garudanih.view.adapter.AdapterInternasional
import com.finpro.garudanih.view.adapter.AdapterLokal
import com.finpro.garudanih.databinding.ActivityTiketInternasionalBinding
import com.finpro.garudanih.view.HomeBottomActivity
import com.finpro.garudanih.viewmodel.TiketViewModel
import dagger.hilt.android.AndroidEntryPoint

@Suppress("MemberVisibilityCanBePrivate", "ReplaceGetOrSet", "unused")
@AndroidEntryPoint
class TiketInternasionalActivity : AppCompatActivity() {
    lateinit var binding : ActivityTiketInternasionalBinding
    lateinit var viewModel : TiketViewModel
    lateinit var adapterIntr : AdapterInternasional
    lateinit var tiketLokalAdapter : AdapterLokal
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
//        viewModel = ViewModelProvider(this).get(TiketViewModel::class.java)
//        viewModel.getLdTiketIntr().observe(this){
//            binding.progressBar.visibility = View.VISIBLE
//            if (it != null){
//                binding.progressBar.visibility = View.GONE
//                binding.rvAllInternational.layoutManager = GridLayoutManager(this,2)
//                adapterTiketIntr.setListTiketInter(it.data.tickets)
//                binding.rvAllInternational.adapter = adapterTiketIntr
//                Toast.makeText(this, "Data Tampil", Toast.LENGTH_SHORT).show()
//            }else{
//                Toast.makeText(this, "Data Tidak Tampil", Toast.LENGTH_SHORT).show()
//            }
//        }
//        viewModel.callApiTiketIntr()
        val viewModel = ViewModelProvider(this).get(TiketViewModel::class.java)
        viewModel.getLdTiketIntr().observe(this){
            binding.loader.visibility = View.VISIBLE
            binding.loader.startShimmer()
            if (it != null){
                binding.loader.visibility = View.GONE
                binding.loader.stopShimmer()
                binding.rvAllInternational.layoutManager = GridLayoutManager(this,2)
                tiketLokalAdapter = AdapterLokal(it.data.tickets)
                binding.rvAllInternational.adapter = tiketLokalAdapter
            }else{
                binding.loader.visibility = View.VISIBLE
                binding.loader.startShimmer()
                Toast.makeText(this, "Data Tidak Tampil", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.callApiTiketIntr()

    }
}
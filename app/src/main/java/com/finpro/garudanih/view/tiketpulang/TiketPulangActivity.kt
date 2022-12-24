package com.finpro.garudanih.view.tiketpulang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.finpro.garudanih.adapter.AdapterInternasional
import com.finpro.garudanih.adapter.AdapterPP
import com.finpro.garudanih.databinding.ActivityTiketPulangBinding
import com.finpro.garudanih.model.responsedetail.ReturnTicket
import com.finpro.garudanih.view.detils.DetailInternasionalActivity
import com.finpro.garudanih.viewmodel.AuthViewModel
import com.finpro.garudanih.viewmodel.ViewModelUser
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TiketPulangActivity : AppCompatActivity() {
    lateinit var binding : ActivityTiketPulangBinding
    lateinit var viewModelUser : ViewModelUser
    lateinit var authViewModel : AuthViewModel
    lateinit var adapterTiketPulang: AdapterPP
    private var tokenUser : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTiketPulangBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        viewModelUser = ViewModelProvider(this).get(ViewModelUser::class.java)
        authViewModel.getToken().observe(this){token->
            if (token != null){
                tokenUser = "Bearer "+token
            }
        }
        setTiketPulang()

        val idTiketPergi = intent.getIntExtra("id",0)
        binding.txtidpul.text = "ID Tiket : "+idTiketPergi.toString()





    }
    fun setTiketPulang(){

//        adapterTiketPulang = AdapterPP {
//            val pindah = Intent(this, DetailInternasionalActivity::class.java)
//            pindah.putExtra("getidpp", it)
//            pindah.putExtra(DetailInternasionalActivity.EXTRA_ID, it.id)
//            startActivity(pindah)
//        }
        val idTiket = intent.getIntExtra("id",0)
        viewModelUser.getDetailByIdObserve().observe(this){
            if (it != null){
                binding.rvTiketPulang.layoutManager = GridLayoutManager(this,2)
                adapterTiketPulang = AdapterPP(it.data.returnTicket)
                binding.rvTiketPulang.adapter = adapterTiketPulang
                adapterTiketPulang.notifyDataSetChanged()

//                binding.rvTiketPulang.layoutManager= LinearLayoutManager(this,
//                    LinearLayoutManager.HORIZONTAL,false)
//                adapterTiketPulang.setListTiket(it.data.returnTicket)
//                binding.rvTiketPulang.adapter = adapterTiketPulang
            }else{
                Toast.makeText(this, "Data Tidak Tampil", Toast.LENGTH_SHORT).show()
            }
        }
        viewModelUser.callDetailById(tokenUser,idTiket)
    }
}

private fun Intent.putExtra(s: String, it: ReturnTicket) {

}

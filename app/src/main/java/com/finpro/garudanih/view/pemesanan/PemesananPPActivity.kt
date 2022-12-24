package com.finpro.garudanih.view.pemesanan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.finpro.garudanih.R
import com.finpro.garudanih.databinding.ActivityPemesananBinding
import com.finpro.garudanih.databinding.ActivityPemesananPpactivityBinding
import com.finpro.garudanih.view.succsess.SuccsesOrderActivity
import com.finpro.garudanih.viewmodel.AuthViewModel
import com.finpro.garudanih.viewmodel.TiketViewModel
import com.finpro.garudanih.viewmodel.UserViewModel
import com.finpro.garudanih.viewmodel.ViewModelUser
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PemesananPPActivity : AppCompatActivity() {
    lateinit var binding : ActivityPemesananPpactivityBinding
    lateinit var viewModelUser : ViewModelUser
    lateinit var authViewModel : AuthViewModel
    private var tokenPaid : String = ""
    private val notificationId = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityPemesananPpactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModelUser = ViewModelProvider(this).get(ViewModelUser::class.java)
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        authViewModel.getToken().observe(this){token->
            if (token != null){
                tokenPaid = "Bearer "+token
            }
        }
        doOrderPP()

    }
    fun doOrderPP(){
        val idTiketPP = intent.getIntExtra("id",0)
        binding.btnPesanTiketPP.setOnClickListener {

            val orderBy = binding.etNamaPemesan.text.toString()
            val ktp = binding.etNik.text.toString()
            val nomorKursiPergi = binding.etNomorKursiPergi.text.toString().toInt()
            val penumpang = binding.etJmlPenumpang.text.toString().toInt()
            val nomorKursiPulang = binding.etNomorKursiPulang.text.toString().toInt()

            if (penumpang == 1){
                viewModelUser.tiketPPObserve().observe(this){
                    if (it != null){
                        startActivity(Intent(this, SuccsesOrderActivity::class.java))
                        Toast.makeText(this,"Berhasil Memesan Tiket", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this,"Gagal Memesan Tiket", Toast.LENGTH_SHORT).show()
                    }
                }

            }
//            viewModelUser.callTiketPP(tokenPaid,orderBy,ktp,nomorKursiPergi,idTiketPP,nomorKursiPulang)



        }
    }
}
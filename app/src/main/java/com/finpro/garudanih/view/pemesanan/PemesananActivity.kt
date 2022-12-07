package com.finpro.garudanih.view.pemesanan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.finpro.garudanih.databinding.ActivityPemesananBinding
import com.finpro.garudanih.view.HomeBottomActivity
import com.finpro.garudanih.viewmodel.AuthViewModel
import com.finpro.garudanih.viewmodel.TiketViewModel
import com.finpro.garudanih.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PemesananActivity : AppCompatActivity() {
    lateinit var binding : ActivityPemesananBinding
    lateinit var viewModel : TiketViewModel
    lateinit var userViewModel : UserViewModel
    lateinit var authViewModel : AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPemesananBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(TiketViewModel::class.java)

        val intent = intent
        val idTiket = intent.getIntExtra("id",0)

        binding.txtIdTiket.text = "ID Tiket : "+idTiket.toString()

        binding.btnPesanTiket.setOnClickListener {
            doOrder()


//            val orderBy = binding.etNamaPemesan.text.toString()
//            val ktp = binding.etNik.text.toString()
//            val nomorKursi = binding.etNomorKursi.text.toString().toInt()
//            orderTiket(orderBy,ktp,nomorKursi)
//            val intent = Intent(this, HomeBottomActivity::class.java)
//            startActivity(intent)
//            Toast.makeText(this,"Berhasil Memesan Tiket", Toast.LENGTH_SHORT).show()
        }





    }
    fun doOrder(){
        val intent = intent
        val idTiket = intent.getIntExtra("id",0)
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnPesanTiket.setOnClickListener {
            val orderBy = binding.etNamaPemesan.text.toString()
            val ktp = binding.etNik.text.toString()
            val nomorKursi = binding.etNomorKursi.text.toString().toInt()
            authViewModel.getToken().observe(this){token->
                if (token != null){
                    if (orderBy.isNotBlank() && ktp.isNotBlank() && nomorKursi.toString().isNotBlank()){
                        userViewModel.orderTiketPesawat("Bearer $token",idTiket,orderBy,ktp,nomorKursi)
                        userViewModel.orderTiketObserve().observe(this){
                            if (it != null){
                                Toast.makeText(this,"Berhasil Memesan Tiket", Toast.LENGTH_SHORT).show()
                            }else{
                                Toast.makeText(this,"Gagal Memesan Tiket", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }


        }


    }


//    fun orderTiket(orderBy:String,ktp:String,numChair:Int){
//        val intent = intent
//        val idTiket = intent.getIntExtra("id",0)
//        viewModel = ViewModelProvider(this).get(TiketViewModel::class.java)
//        viewModel.callPostOrder(idTiket,orderBy,ktp,numChair)
//        viewModel.postTiketOrder().observe(this,{
//            if (it != null){
//                Toast.makeText(this,"Berhasil Memesan Tiket", Toast.LENGTH_SHORT).show()
//            }
//        })
//        finish()
//    }
//    fun getToken(orderBy:String,ktp:String,numChair:Int){
//        viewModel = ViewModelProvider(this).get(TiketViewModel::class.java)
//        val intent = intent
//        val idTiket = intent.getIntExtra("id",0)
//        val authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
//        authViewModel.getToken().observe(this){
//            if (it != null){
//                viewModel.callPostOrder("Bearer $it",idTiket,orderBy,ktp,numChair)
//            }else{
//                Log.d("TOKEN","Token Null")
//            }
//        }
//    }


}
package com.finpro.garudanih.view.pemesanan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.finpro.garudanih.adapter.AdapterHistory
import com.finpro.garudanih.databinding.ActivityPemesananBinding
import com.finpro.garudanih.view.HomeBottomActivity
import com.finpro.garudanih.view.fragments.history.HistoryFragment
import com.finpro.garudanih.view.succsess.SuccsesOrderActivity
import com.finpro.garudanih.view.transaksi.TransaksiActivity
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
    private var tokenPaid : String = ""
    private var transId : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPemesananBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(TiketViewModel::class.java)
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        authViewModel.getToken().observe(this){token->
            if (token != null){
                tokenPaid = "Bearer "+token
            }
        }



        val intent = intent
        val idTiket = intent.getIntExtra("id",0)

        binding.ivBack.setOnClickListener {
            startActivity(Intent(this, HomeBottomActivity::class.java))
        }
        binding.txtIdTiket.text = "ID Tiket : "+idTiket.toString()


        binding.btnPesanTiket.setOnClickListener {
            doOrder()
        }


    }
    fun doOrder(){
        val intent = intent
        val harga = intent.getIntExtra("harga",0)
        val kota = intent.getStringExtra("destinasi")
        val keberangkatan = intent.getStringExtra("departure")
        val jadwal = intent.getStringExtra("jadwal")
        val idTiket = intent.getIntExtra("id",0)
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnPesanTiket.setOnClickListener {
            val orderBy = binding.etNamaPemesan.text.toString()
            val ktp = binding.etNik.text.toString()
            val nomorKursi = binding.etNomorKursi.text.toString().toInt()
            val penumpang = binding.etJmlPenumpang.text.toString().toInt()
            if (penumpang == 1){
                userViewModel.orderTiketObserve().observe(this){
                    if (it != null){
                        binding.txtTotalHarga.text= harga.toString()

                        startActivity(Intent(this, SuccsesOrderActivity::class.java))
                        Toast.makeText(this,"Berhasil Memesan Tiket", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this,"No Kursi Sudah Dipesan Oleh User Lain", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            if (penumpang == 2){
                binding.etNik.setText("")
                binding.etNomorKursi.setText("")
                userViewModel.orderTiketObserve().observe(this){
                        if (it != null){
                            binding.etJmlPenumpang.setText("1")
                            Toast.makeText(this,"Silahkan Isi Identitas Penumpang Selanjutnya", Toast.LENGTH_SHORT).show()

                        }else{
                            Toast.makeText(this,"No Kursi Sudah Dipesan Oleh User Lain", Toast.LENGTH_SHORT).show()
                        }
                }
                if (penumpang == 1){
                    userViewModel.orderTiketObserve().observe(this){
                        if (it != null){
                            val hargaDua = harga*2
                            binding.txtTotalHarga.text = hargaDua.toString()
                            startActivity(Intent(this, SuccsesOrderActivity::class.java))

                            Toast.makeText(this,"Berhasil Memesan Tiket", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this,"No Kursi Sudah Dipesan Oleh User Lain", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            }
            if (penumpang == 3){
                binding.etNik.setText("")
                binding.etNomorKursi.setText("")
                userViewModel.orderTiketObserve().observe(this){
                    if (it != null){
                        binding.etJmlPenumpang.setText("2")
                        Toast.makeText(this,"Silahkan Isi Identitas Penumpang Selanjutnya", Toast.LENGTH_SHORT).show()

                    }else{
                        Toast.makeText(this,"No Kursi Sudah Dipesan Oleh User Lain", Toast.LENGTH_SHORT).show()
                    }
                }
                if (penumpang == 2){
                    userViewModel.orderTiketObserve().observe(this){
                        if (it != null){
                            binding.etJmlPenumpang.setText("1")
                            Toast.makeText(this,"Silahkan Isi Identitas Penumpang Selanjutnya", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this,"No Kursi Sudah Dipesan Oleh User Lain", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                if (penumpang == 1){
                    userViewModel.orderTiketObserve().observe(this){
                        if (it != null){
                            val hargaTiga = harga*3
                            binding.txtTotalHarga.text = hargaTiga.toString()

                            startActivity(Intent(this, SuccsesOrderActivity::class.java))


                            Toast.makeText(this,"Berhasil Memesan Tiket", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this,"No Kursi Sudah Dipesan Oleh User Lain", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            }


            if (penumpang > 3){
                Toast.makeText(this,"Maaf Pemesanan Penumpang Maksimal 3 kali", Toast.LENGTH_SHORT).show()
            }

            authViewModel.getToken().observe(this){token->
                if (token != null){
                    if (orderBy.isNotBlank() && ktp.isNotBlank() && nomorKursi.toString().isNotBlank()){
                        userViewModel.orderTiketPesawat("Bearer $token",idTiket,orderBy,ktp,nomorKursi)

                    }
                }
            }
//            userViewModel.orderTiketObserve().observe(this){
//                if (it != null){
//                    Toast.makeText(this,"Berhasil Memesan Tiket", Toast.LENGTH_SHORT).show()
//                }else{
//                    Toast.makeText(this,"No Kursi Sudah Dipesan Oleh User Lain", Toast.LENGTH_SHORT).show()
//                }
//            }


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
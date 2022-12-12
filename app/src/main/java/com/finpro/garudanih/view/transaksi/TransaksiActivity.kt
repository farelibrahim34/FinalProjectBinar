package com.finpro.garudanih.view.transaksi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.finpro.garudanih.R
import com.finpro.garudanih.databinding.ActivityTransaksiBinding
import com.finpro.garudanih.view.HomeBottomActivity
import com.finpro.garudanih.view.fragments.history.HistoryFragment
import com.finpro.garudanih.view.tiketorder.TiketOrderActivity
import com.finpro.garudanih.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransaksiActivity : AppCompatActivity() {
    lateinit var binding : ActivityTransaksiBinding
    lateinit var userViewModel : UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransaksiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        getDataTiket()


    }
    fun getDataTiket(){
        val getData = intent
        val getDeparture = getData.getStringExtra("departure")
        val getDestinasi = getData.getStringExtra("destination")
        val getIdTrans = getData.getIntExtra("idtrans",0)
        val getharga = getData.getIntExtra("harga",0)
        val getPaid = getData.getBooleanExtra("paid",false)
        val getDestiCode = getData.getStringExtra("desticode")
        val getDeparCode = intent.getStringExtra("deparcode")
        val getOrderBy = intent.getStringExtra("orderby")
        val getClass = intent.getStringExtra("class")
        val getKtp = intent.getStringExtra("ktp")
        val getKursi = intent.getIntExtra("kursi",0)
        val getNoTerbang = intent.getStringExtra("nopenerbangan")

        binding.txtKeberangkatan.text = getDeparture
        binding.txtTujuan.text = getDestinasi
        binding.txtHargaTransaksi.text = getharga.toString()
        binding.txtPaid.text = getPaid.toString()
        binding.txtIdTrans.text = getIdTrans.toString()

        if (getPaid == false){
            binding.txtPaid.text = "Belum Bayar"
        }else if (getPaid == true){
            binding.txtPaid.text = "Sudah Bayar"
        }

        if (getPaid == false){
            binding.btnBayar.setText("Bayar Sekarang")
            binding.btnBayar.setOnClickListener {
                userViewModel.paidUser(getIdTrans)
                userViewModel.paidUserObserve().observe(this){
                    if (it != null){
                        Toast.makeText(this,"Berhasil Melakukan Pembayaran", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }else {
            binding.btnBayar.setText("Lihat Tiket")
            startActivity(Intent(this, TiketOrderActivity::class.java))
        }


//        binding.btnBayar.setOnClickListener {
//            userViewModel.paidUser(getIdTrans)
//            userViewModel.paidUserObserve().observe(this){
//                if (it != null){
//                    binding.btnBayar.setText("Lihat Tiket")
//                    binding.btnBayar.setOnClickListener {
//                        startActivity(Intent(this, TiketOrderActivity::class.java))
//                    }
//                }
//            }
//        }

    }
}




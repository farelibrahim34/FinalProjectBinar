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
        val getPaid = getData.getStringExtra("paid")

        binding.txtKeberangkatan.text = getDeparture
        binding.txtTujuan.text = getDestinasi
        binding.txtHargaTransaksi.text = getharga.toString()
        binding.txtPaid.text = getPaid
        binding.txtIdTrans.text = getIdTrans.toString()

        binding.btnBayar.setOnClickListener {
            userViewModel.paidUser(getIdTrans)
            userViewModel.paidUserObserve().observe(this){
                if (it != null){
                    Toast.makeText(this,"Silahkan Cek di Halaman History Pemesanan", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, HomeBottomActivity::class.java))
                }
            }
        }

    }
}
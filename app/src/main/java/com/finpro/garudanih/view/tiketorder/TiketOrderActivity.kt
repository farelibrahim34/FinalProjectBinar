package com.finpro.garudanih.view.tiketorder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isGone
import androidx.lifecycle.ViewModelProvider
import com.finpro.garudanih.R
import com.finpro.garudanih.databinding.ActivityTiketOrderBinding
import com.finpro.garudanih.view.HomeBottomActivity
import com.finpro.garudanih.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TiketOrderActivity : AppCompatActivity() {
    lateinit var binding : ActivityTiketOrderBinding
    lateinit var userViewModel : UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTiketOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val getOrderBy = intent.getStringExtra("orderby")
        val getKtp = intent.getStringExtra("ktp")
        val getKursi = intent.getIntExtra("kursi",0)
        val getDestiCode = intent.getStringExtra("desticode")
        val getDeparCode = intent.getStringExtra("deparcode")
        val getJadwal = intent.getStringExtra("jadwal")
        val getClass = intent.getStringExtra("class")
        val getPaid = intent.getBooleanExtra("paid",false)
        val getNoTerbang = intent.getStringExtra("nopenerbangan")
        val getIdTrans = intent.getIntExtra("idtrans",0)

        binding.CodePenerbangan.text = getNoTerbang
        binding.tgl.text = getJadwal
        binding.JenisClass.text = getClass
        binding.noKursi.text = getKursi.toString()
        binding.txtNoKtp.text = "Nomor KTP : "+getKtp
        binding.namapenumpang.text = getOrderBy
        binding.Asal.text = getDeparCode
        binding.Tujuan.text = getDestiCode
        if (getPaid == true){
            binding.statusBook.text = "Sudah Bayar"
        }else if (getPaid == false){
            binding.statusBook.text = "Belum Bayar"
        }

        binding.ivBackDetail.setOnClickListener {
            startActivity(Intent(this, HomeBottomActivity::class.java))
            Toast.makeText(this,"Terimakasih Anda Sudah Memesan Tiket Melalui GarudaNih.", Toast.LENGTH_SHORT).show()
        }
        if (getPaid == false){
            binding.btnBayar5.setText("Bayar Sekarang")
            binding.btnBayar5.setOnClickListener {
                userViewModel.paidUser(getIdTrans)
                userViewModel.paidUserObserve().observe(this){
                    if (it != null){
                        Toast.makeText(this,"Berhasil Melakukan Pembayaran", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,HomeBottomActivity::class.java))
                    }
                }
            }
        }else{
            binding.btnBayar5.isGone = true
        }
    }
}
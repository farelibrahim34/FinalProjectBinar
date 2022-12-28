package com.finpro.garudanih.view.pemesanan

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import com.finpro.garudanih.R
import com.finpro.garudanih.databinding.ActivityPemesananBinding
import com.finpro.garudanih.view.HomeBottomActivity
import com.finpro.garudanih.view.succsess.SuccsesOrderActivity
import com.finpro.garudanih.viewmodel.AuthViewModel
import com.finpro.garudanih.viewmodel.TiketViewModel
import com.finpro.garudanih.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@Suppress("MemberVisibilityCanBePrivate", "ReplaceGetOrSet", "ConvertToStringTemplate",
    "PrivatePropertyName", "unused"
)
@AndroidEntryPoint
class PemesananActivity : AppCompatActivity() {
    lateinit var binding : ActivityPemesananBinding
    lateinit var viewModel : TiketViewModel
    lateinit var userViewModel : UserViewModel
    lateinit var authViewModel : AuthViewModel
    private var tokenPaid : String = ""
    private val CHANNEL_ID = "chanel_id_example_01"
    private val notificationId = 101
    private var transId : Int = 0
    private var id :Int?=null
    companion object{
        const val  EXTRA_ID = "extra_id"
    }


    @SuppressLint("SetTextI18n")
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

        createNotificationChannel()
        binding.btnPesanTiket.setOnClickListener {
            doOrder()
            sendNotification()
        }


    }
    private fun doOrder(){
        val intent = intent
        val harga = intent.getIntExtra("harga",0)
        val kota = intent.getStringExtra("destinasi")
        val keberangkatan = intent.getStringExtra("departure")
        val jadwal = intent.getStringExtra("jadwal")
        val idTiket = intent.getIntExtra("id",0)
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        id = intent.getIntExtra(EXTRA_ID, 0)

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
                            Toast.makeText(this,"Berhasil Memesan Tiket", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, SuccsesOrderActivity::class.java))
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
                        userViewModel.orderTiketPesawat("Bearer $token",idTiket,orderBy,ktp,nomorKursi)
                }
            }


        }
    }

    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Notification Title"
            val descriptionText = "Notification Description"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID,name,importance).apply {
                description= descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(){

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_gn_circle)
            .setContentTitle("Berhasil Booking Tiket")
            .setContentText("Silakan Lanjuktkan Pembayaran")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)){
            notify(notificationId, builder.build())
        }
    }


}
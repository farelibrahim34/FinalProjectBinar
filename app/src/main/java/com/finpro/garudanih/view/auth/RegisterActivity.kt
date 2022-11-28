package com.finpro.garudanih.view.auth

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.finpro.garudanih.databinding.ActivityRegisterBinding
import android.widget.DatePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.finpro.garudanih.datastore.DataStoreLogin
import com.finpro.garudanih.viewmodel.ViewModelUser
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private lateinit var datalogin : DataStoreLogin
    private lateinit var viewModel : ViewModelUser

    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)


        binding.ivBackDetail.setOnClickListener {
            val toLogin = Intent(this,LoginActivity::class.java)
            startActivity(toLogin)
        }

        binding.btnRegister.setOnClickListener{
//            registerDatastore()
            registerApi()
            val toHome = Intent(this, LoginActivity::class.java)
            startActivity(toHome)
        }
    }

    private fun registerDatastore() {
            val saveNamaP = binding.etUsername.toString()
            val saveUsername = binding.etUsername.toString()
            val saveEmail = binding.etEmail.toString()
            val savePw = binding.etPassword.toString()
            val saveUpw = binding.etConfPassword.toString()
            GlobalScope.launch {
                datalogin.saveData(saveUsername,saveEmail,savePw,saveUpw)
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
    }
    fun registerApi(){
        val saveName = binding.etUsername.text.toString()
        val saveEmail = binding.etEmail.text.toString()
        val savePw = binding.etPassword.text.toString()
        val saveUpw = binding.etConfPassword.text.toString()
        if(saveName.isEmpty() || saveEmail.isEmpty() || savePw.isEmpty() || saveUpw.isEmpty()) {
            Toast.makeText(this, "ISI TERLEBIH DAHULU", Toast.LENGTH_SHORT).show()
        }else{
            if (savePw == saveUpw){
                viewModel.callPostApiUser(saveName,saveEmail,savePw)
                viewModel.postLiveDataUser().observe(this){
                    if (it != null){
                        Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }else{
                Toast.makeText(this, "Password tidak sama", Toast.LENGTH_SHORT).show()
            }
        }
        Toast.makeText(this,"Anda Telah Berhasil Membuat Akun", Toast.LENGTH_SHORT).show()

    }

}
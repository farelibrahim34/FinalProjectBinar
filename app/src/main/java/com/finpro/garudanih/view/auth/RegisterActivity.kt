package com.finpro.garudanih.view.auth

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.finpro.garudanih.databinding.ActivityRegisterBinding
import android.widget.DatePicker
import com.finpro.garudanih.datastore.DataStoreLogin
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private lateinit var datalogin : DataStoreLogin

    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.ivBackDetail.setOnClickListener {
            val toLogin = Intent(this,LoginActivity::class.java)
            startActivity(toLogin)
        }

        binding.btnRegister.setOnClickListener{
            registerDatastore()
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
                datalogin.saveData(saveNamaP,saveUsername,saveEmail,savePw,saveUpw)
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
    }
}
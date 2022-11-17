package com.finpro.garudanih.view.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.asLiveData
import com.finpro.garudanih.databinding.ActivityLoginBinding
import com.finpro.garudanih.databinding.FragmentHomeBinding
import com.finpro.garudanih.datastore.DataStoreLogin

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var dataLogin : DataStoreLogin
    private lateinit var username : String
    private lateinit var password : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataLogin = DataStoreLogin(this)

        dataLogin.userName.asLiveData().observe(this,{
            username = it.toString()
        })

        dataLogin.userPw.asLiveData().observe(this,{
            password = it.toString()
        })

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            val edUser = binding.etUsername.text.toString()
            val edPass = binding.etPassword.text.toString()
            if (edUser.isEmpty()) {
                binding.etUsername.setError("isi username dahulu")
            } else if (edPass.isEmpty()) {
                binding.etPassword.setError("isi password dahulu")
            } else if (edUser == username) {
                startActivity(Intent(this, FragmentHomeBinding::class.java))
                Toast.makeText(this, "anda berhasil login", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Username dan password salah", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
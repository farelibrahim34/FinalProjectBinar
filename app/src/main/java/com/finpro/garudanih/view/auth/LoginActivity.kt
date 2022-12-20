package com.finpro.garudanih.view.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.finpro.garudanih.databinding.ActivityLoginBinding
import com.finpro.garudanih.datastore.DataStoreLogin
import com.finpro.garudanih.utils.LoginUtil
import com.finpro.garudanih.view.HomeBottomActivity
import com.finpro.garudanih.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@Suppress("ReplaceGetOrSet", "MoveLambdaOutsideParentheses")
@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var dataLogin : DataStoreLogin
    private lateinit var username : String
    private lateinit var password : String
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

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
//            startActivity(Intent(this, HomeBottomActivity::class.java))
            login()
        }
    }

    private fun login(){
        binding.btnLogin.setOnClickListener {
            val email = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val validasi = LoginUtil.validateUserlogin(email,password)
            if (validasi == "success"){
                authViewModel.doLogin(email,password)
                authViewModel.ldSigIn().observe(this){
                    if (it != null){
                        authViewModel.setToken(it.token)
                        Toast.makeText(this,"Login Sukses", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,HomeBottomActivity::class.java))
                    }else{
                        Toast.makeText(this,"Login Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }
}
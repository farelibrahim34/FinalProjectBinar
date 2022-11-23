package com.finpro.garudanih.view.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.asLiveData
import com.finpro.garudanih.databinding.ActivityLoginBinding
import androidx.core.widget.addTextChangedListener
import com.finpro.garudanih.datastore.DataStoreLogin
import com.finpro.garudanih.view.HomeBottomActivity

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

        binding.etPassword.addTextChangedListener{password ->
            if (isPasswordValid) {
                validate()
                binding.passworInputLayout.error = null
            } else {
                binding.passworInputLayout.error = "Must Have A-Z a-z 0-9"
            }

        }

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, HomeBottomActivity::class.java))
        }
    }
    private fun validate(){
            binding.etUsername.text.toString().isNotBlank()
                    && binding.etPassword.text.toString().isNotBlank()
                    && isPasswordValid
    }

    private val isPasswordValid: Boolean get(){
        val passText = binding.etPassword.text.toString()
        return passText.contains("[a-z]".toRegex())
                && passText.contains("[A-Z]".toRegex())
                && passText.contains("[0-9]".toRegex())
                && passText.length >= 8
    }
}
package com.finpro.garudanih.view.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.finpro.garudanih.datastore.DataStoreLogin
import com.finpro.garudanih.databinding.ActivityRegisterBinding
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private lateinit var datalogin : DataStoreLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.ivBackDetail.setOnClickListener {
            val toLogin = Intent(this,LoginActivity::class.java)
            startActivity(toLogin)
        }

        binding.btnRegister.setOnClickListener{password ->
            if (isPasswordValid) {
                validate()
                binding.passwordInputLayout.error = null
                binding.passwordConfInputLayout.error = null
            } else {
                binding.passwordInputLayout.error = "Character Tidak Sesuai"
                binding.passwordConfInputLayout.error = "Character Tidak Sesuai"
            }
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }

    private fun validate(){
        binding.btnRegister.isEnabled =
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
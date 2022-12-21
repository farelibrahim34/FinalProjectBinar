package com.finpro.garudanih.view.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.finpro.garudanih.databinding.ActivityRegisterBinding
import com.finpro.garudanih.viewmodel.ViewModelUser
import dagger.hilt.android.AndroidEntryPoint

@Suppress("MemberVisibilityCanBePrivate")
@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private lateinit var viewModel : ViewModelUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ViewModelUser::class.java]

        binding.ivBackDetail.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }

        formValidataion()

        binding.btnRegister.setOnClickListener{
            registerApi()
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.etPassword.addTextChangedListener{
            if (isPasswordValid) {
                validate()
                binding.passwordInputLayout.error = null
            } else {
                binding.etPassword.error = "Must Have A-Z a-z 0-9"
            }
        }
    }

    private fun formValidataion(){
        binding.etConfPassword.addTextChangedListener { confirmPassword ->
            if (confirmPassword.toString() != binding.etPassword.text.toString()){
                binding.btnRegister.isClickable = false
                binding.passwordConfInputLayout.isEndIconVisible = false
                binding.etConfPassword.error ="Confirm password is not match"
            }else{
                binding.btnRegister.isClickable = true
                binding.passwordConfInputLayout.isEndIconVisible = true
            }
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
                && passText.length >= 5
    }

    fun registerApi(){
        val saveName = binding.etUsername.text.toString()
        val saveEmail = binding.etEmail.text.toString()
        val savePw = binding.etPassword.text.toString()
        val saveUpw = binding.etConfPassword.text.toString()
        if(saveName.isEmpty() || saveEmail.isEmpty() || savePw.isEmpty() || saveUpw.isEmpty()) {
            Toast.makeText(this, "Field Harus Terisi", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(this, "Anda Gagal Register", Toast.LENGTH_SHORT).show()
            }
        }
        Toast.makeText(this,"Anda Telah Berhasil Membuat Akun", Toast.LENGTH_SHORT).show()
    }

}
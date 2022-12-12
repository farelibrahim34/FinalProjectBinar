package com.finpro.garudanih.view.wrapper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.ViewModelProvider
import com.finpro.garudanih.R
import com.finpro.garudanih.databinding.ActivitySplashScreenBinding
import com.finpro.garudanih.utils.CheckUserUtil
import com.finpro.garudanih.view.HomeBottomActivity
import com.finpro.garudanih.view.auth.LoginActivity
import com.finpro.garudanih.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    lateinit var authViewModel : AuthViewModel
    lateinit var binding : ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        Handler().postDelayed({
            sudahlogin()
            finish()
        },3000)
    }
    private fun sudahlogin(){
        authViewModel.getToken().observe(this){
            if (it != null){
                val validasi = CheckUserUtil.validateUser(it)
                if (validasi){
                    startActivity(Intent(this, HomeBottomActivity::class.java))
                }else{
                    startActivity(Intent(this, ViewpagerActivity::class.java))
                }
            }
        }

        authViewModel.getNoHp().observe(this){
            if (it != null){
                if (!it.equals("undefined")){
                    startActivity(Intent(this, HomeBottomActivity::class.java))
                }else{
                    startActivity(Intent(this, ViewpagerActivity::class.java))
                }
            }
        }

    }
}
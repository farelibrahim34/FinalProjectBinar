package com.finpro.garudanih.view.wrapper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.finpro.garudanih.R
import com.finpro.garudanih.databinding.ActivitySplashScreenBinding
import com.finpro.garudanih.utils.CheckUserUtil
import com.finpro.garudanih.viewmodel.AuthViewModel

class SplashScreenActivity : AppCompatActivity() {
    lateinit var authViewModel : AuthViewModel
    lateinit var binding : ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler().postDelayed({
            startActivity(Intent(this, ViewpagerActivity::class.java))
            finish()
        },3000)
    }
    private fun sudahlogin(){
        authViewModel.getToken().observe(this){
            if (it != null){
                val validasi = CheckUserUtil.validateUser(it)
                if (validasi){
                    startActivity(Intent(this, ViewpagerActivity::class.java))
                }else{

                }
            }
        }

    }
}
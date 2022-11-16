package com.finpro.garudanih.view.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.finpro.garudanih.databinding.ActivityOnBoardingSatuBinding
import com.finpro.garudanih.databinding.ActivityOnBoardingTigaBinding
import com.finpro.garudanih.view.auth.LoginActivity
import com.finpro.garudanih.view.auth.RegisterActivity

class OnBoardingTiga : AppCompatActivity() {

    private lateinit var binding : ActivityOnBoardingTigaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingTigaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val toRegist = Intent(this, RegisterActivity::class.java)
            startActivity(toRegist)
        }

        binding.tvLogin.setOnClickListener {
            val toLogin = Intent(this,LoginActivity::class.java)
            startActivity(toLogin)
        }
    }
}
package com.finpro.garudanih.view.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.finpro.garudanih.R
import com.finpro.garudanih.databinding.ActivityOnBoardingDuaBinding

class OnBoardingDua : AppCompatActivity() {

    private lateinit var binding : ActivityOnBoardingDuaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingDuaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvNext.setOnClickListener {
            val toOnboardThird = Intent(this,OnBoardingTiga::class.java)
            startActivity(toOnboardThird)
        }
    }
}
package com.finpro.garudanih.view.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.finpro.garudanih.databinding.ActivityOnBoardingSatuBinding
import com.finpro.garudanih.databinding.ActivityOnBoardingTigaBinding

class OnBoardingTiga : AppCompatActivity() {

    private lateinit var binding : ActivityOnBoardingTigaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingTigaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
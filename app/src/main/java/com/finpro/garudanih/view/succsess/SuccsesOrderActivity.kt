package com.finpro.garudanih.view.succsess

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.finpro.garudanih.databinding.ActivitySuccsesOrderBinding
import com.finpro.garudanih.view.HomeBottomActivity

class SuccsesOrderActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySuccsesOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccsesOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOrderLagi.setOnClickListener {
            startActivity(Intent(this, HomeBottomActivity::class.java))
        }
    }
}
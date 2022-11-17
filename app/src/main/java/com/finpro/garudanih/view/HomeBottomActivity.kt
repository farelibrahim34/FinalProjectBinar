package com.finpro.garudanih.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.finpro.garudanih.R
import androidx.navigation.ui.setupWithNavController
import com.finpro.garudanih.databinding.ActivityHomeBottomBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeBottomActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBottomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBottomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val navView: BottomNavigationView = binding.navView

        val navController =
            findNavController(R.id.nav_host_fragment_activity_home_bottom_navigation)
        navView.setupWithNavController(navController)


    }
}
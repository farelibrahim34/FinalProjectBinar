package com.finpro.garudanih.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.finpro.garudanih.R
import androidx.navigation.ui.setupWithNavController
import com.finpro.garudanih.databinding.ActivityHomeBottomBinding
import com.finpro.garudanih.view.detils.TiketInternasionalActivity
import com.finpro.garudanih.view.fragments.history.HistoryFragment
import com.finpro.garudanih.view.fragments.wishlist.OrderFragmentInterface
import com.finpro.garudanih.wishlist.WishlistActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeBottomActivity : AppCompatActivity(), OrderFragmentInterface {

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

//        binding.wishlist.setOnClickListener {
//            startActivity(Intent(this, WishlistActivity::class.java))
//        }

    }

    override fun onClickOrder() {
        supportFragmentManager.beginTransaction()
            .add(HistoryFragment(), HistoryFragment.TAG)
            .addToBackStack(null)
            .commit()
    }
}
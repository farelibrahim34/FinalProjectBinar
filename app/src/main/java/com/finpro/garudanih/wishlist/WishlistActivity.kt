package com.finpro.garudanih.wishlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.finpro.garudanih.databinding.ActivityWishlistBinding
import com.finpro.garudanih.wishlist.fragment.DomesticTablayoutFragment
import com.finpro.garudanih.wishlist.fragment.InternatonalTablayoutFragment
import com.finpro.garudanih.wishlist.fragment.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout

class WishlistActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWishlistBinding
    private lateinit var pager: ViewPager // creating object of ViewPager
    private lateinit var tab: TabLayout  // creating object of TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWishlistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set the references of the declared objects above
        pager = binding.viewPager
        tab = binding.tabs

        // To make our toolbar show the application
        // we need to give it to the ActionBar

        // Initializing the ViewPagerAdapter
        val adapter = ViewPagerAdapter(supportFragmentManager)

        // add fragment to the list
        adapter.addFragment(DomesticTablayoutFragment(), "Domestic Flights")
        adapter.addFragment(InternatonalTablayoutFragment(), "International Flights")

        // Adding the Adapter to the ViewPager
        pager.adapter = adapter

        // bind the viewPager with the TabLayout.
        tab.setupWithViewPager(pager)
    }
}
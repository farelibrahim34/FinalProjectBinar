package com.finpro.garudanih.wishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.finpro.garudanih.adapter.AdapterInternasional
import com.finpro.garudanih.adapter.AdapterWishListInternasional
import com.finpro.garudanih.adapter.AdapterWishListLoc
import com.finpro.garudanih.databinding.ActivityWishlistBinding
import com.finpro.garudanih.wishlistinternasional.DatabaseWishPesawatInternasional
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WishlistActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWishlistBinding
    private var databaseWishPesawatLoc : DatabaseWishPesawatLoc? = null
    private var databaseWishPesawatInternasional : DatabaseWishPesawatInternasional? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWishlistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseWishPesawatLoc = DatabaseWishPesawatLoc.getInstance(this)
        databaseWishPesawatInternasional = DatabaseWishPesawatInternasional.getInstance(this)

        getWishlistLocal()
        getWishListInter()
    }
    fun getWishlistLocal(){
        binding.rvLocal.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        GlobalScope.launch {
            val wishList = databaseWishPesawatLoc?.WishDao()?.getWishPesawat()
            runOnUiThread{
                wishList.let {
                    val adapter = AdapterWishListLoc(it!!)
                    binding.rvLocal.adapter = adapter
                }
            }
        }
    }

    fun getWishListInter(){
        binding.rvInternational.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        GlobalScope.launch {
            val wishListInter = databaseWishPesawatInternasional?.WishInternasionalDao()?.getWishPesawatInternasional()
            runOnUiThread {
                wishListInter.let {
                    val adap = AdapterWishListInternasional(it!!)
                    binding.rvInternational.adapter = adap
                }
            }
        }
    }
}
package com.finpro.garudanih.wishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.finpro.garudanih.adapter.AdapterWishListLoc
import com.finpro.garudanih.databinding.ActivityWishlistBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WishlistActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWishlistBinding
    private var databaseWishPesawatLoc : DatabaseWishPesawatLoc? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWishlistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseWishPesawatLoc = DatabaseWishPesawatLoc.getInstance(this)

        getWishlistLocal()
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
}
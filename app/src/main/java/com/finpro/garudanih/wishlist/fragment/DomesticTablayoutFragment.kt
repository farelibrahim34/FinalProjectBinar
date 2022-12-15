package com.finpro.garudanih.wishlist.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.finpro.garudanih.R
import com.finpro.garudanih.adapter.AdapterWishListLoc
import com.finpro.garudanih.databinding.FragmentDomesticTablayoutBinding
import com.finpro.garudanih.wishlist.DatabaseWishPesawatLoc
import com.finpro.garudanih.wishlist.WishlistActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class DomesticTablayoutFragment : Fragment() {

    private lateinit var binding : FragmentDomesticTablayoutBinding
    private var databaseWishPesawatLoc : DatabaseWishPesawatLoc? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDomesticTablayoutBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        databaseWishPesawatLoc = DatabaseWishPesawatLoc.getInstance(requireActivity())
        getWishlistLocal()
    }

    fun Fragment?.runOnUiThread(action: () -> Unit) {
        this ?: return
        if (!isAdded) return // Fragment not attached to an Activity
        activity?.runOnUiThread(action)
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getWishlistLocal(){
        binding.rvLocal.layoutManager = GridLayoutManager(requireActivity(), 2)
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
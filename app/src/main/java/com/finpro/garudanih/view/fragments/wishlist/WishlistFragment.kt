package com.finpro.garudanih.view.fragments.wishlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.finpro.garudanih.R
import com.finpro.garudanih.adapter.AdapterListPesawat
import com.finpro.garudanih.adapter.AdapterOrder
import com.finpro.garudanih.databinding.FragmentWishlistBinding
import com.finpro.garudanih.model.ListPesawat

class WishlistFragment : Fragment() {

    private lateinit var binding : FragmentWishlistBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWishlistBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = arrayListOf(
            ListPesawat("China",2000000,"16 Agustus","24/100",R.drawable.pesawat,"pending"),
            ListPesawat("Malaysia",2000000,"16 Agustus","24/100",R.drawable.pesawat,"pending"),
            ListPesawat("Thailand",2000000,"16 Agustus","24/100",R.drawable.pesawat,"pending"),
            ListPesawat("Singapura",2000000,"16 Agustus","24/100",R.drawable.pesawat,"pending"),
        )
        binding.rvOrder.adapter = AdapterOrder(list)
        binding.rvOrder.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
    }
}
package com.finpro.garudanih.view.wrapper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.finpro.garudanih.databinding.ItemPage2Binding

class fragment_two : Fragment(){

    private lateinit var binding: ItemPage2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemPage2Binding.inflate(layoutInflater, container, false)
        return binding.root
    }
}
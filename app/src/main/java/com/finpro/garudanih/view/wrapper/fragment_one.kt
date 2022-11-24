package com.finpro.garudanih.view.wrapper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.finpro.garudanih.databinding.ItemPageBinding

class fragment_one : Fragment(){

    private lateinit var binding : ItemPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ItemPageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}
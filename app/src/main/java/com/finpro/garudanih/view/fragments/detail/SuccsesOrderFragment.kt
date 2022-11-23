package com.finpro.garudanih.view.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.finpro.garudanih.R
import com.finpro.garudanih.databinding.FragmentDetailBinding
import com.finpro.garudanih.databinding.FragmentSuccsesOrderBinding

class SuccsesOrderFragment : Fragment() {

    private lateinit var binding : FragmentSuccsesOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentSuccsesOrderBinding.inflate(inflater, container, false)
        return binding.root
    }
}
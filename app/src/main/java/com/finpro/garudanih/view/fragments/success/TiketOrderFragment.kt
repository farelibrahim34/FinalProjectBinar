package com.finpro.garudanih.view.fragments.success

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.finpro.garudanih.R
import com.finpro.garudanih.databinding.FragmentTiketOrderBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TiketOrderFragment : Fragment() {

    private lateinit var binding : FragmentTiketOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentTiketOrderBinding.inflate(inflater, container, false)
        return binding.root
    }
}
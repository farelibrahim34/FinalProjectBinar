package com.finpro.garudanih.view.wrapper.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.finpro.garudanih.databinding.FragmentVpHomeOneBinding
import dagger.hilt.android.AndroidEntryPoint

@Suppress("RedundantNullableReturnType")
@AndroidEntryPoint
class FragmentVpHomeOne : Fragment() {
    lateinit var binding : FragmentVpHomeOneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVpHomeOneBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}
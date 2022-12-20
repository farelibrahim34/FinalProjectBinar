package com.finpro.garudanih.view.wrapper.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.finpro.garudanih.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentVpHomeThree : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vp_home_three, container, false)
    }

}
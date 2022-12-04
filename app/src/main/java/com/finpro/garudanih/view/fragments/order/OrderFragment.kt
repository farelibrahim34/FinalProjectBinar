package com.finpro.garudanih.view.fragments.order

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.finpro.garudanih.R
import com.finpro.garudanih.databinding.FragmentOrderBinding
import com.finpro.garudanih.databinding.FragmentSuccessOrderBinding
import com.finpro.garudanih.view.fragments.success.SuccessOrderFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderFragment : Fragment() {

    private lateinit var binding : FragmentOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOrderBinding.inflate(layoutInflater, container, false)
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCari.setOnClickListener {
            startActivity(Intent(context, SuccessOrderFragment::class.java))
        }
    }
}
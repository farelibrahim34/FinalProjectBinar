package com.finpro.garudanih.view.fragments.success

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.finpro.garudanih.R
import com.finpro.garudanih.databinding.FragmentSuccessOrderBinding
import com.finpro.garudanih.view.fragments.wishlist.OrderFragmentInterface
import com.finpro.garudanih.view.fragments.wishlist.WishlistFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuccessOrderFragment : Fragment() {

    companion object {
        const val TAG = "SuccessOrderFragment"

    }

    private lateinit var binding : FragmentSuccessOrderBinding
    private var orderInterface: OrderFragmentInterface? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OrderFragmentInterface){
            orderInterface = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this
        binding = FragmentSuccessOrderBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}
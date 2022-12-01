package com.finpro.garudanih.view.fragments.wishlist

import android.content.Context
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WishlistFragment : Fragment() {

    companion object {
        const val TAG = "WishlistFragment"

        fun newInstance(dataNama: String, dataRekening : String): WishlistFragment {
            val fragment = WishlistFragment()
            val bundle = Bundle().apply {
                putString("nama", dataNama)
                putString("noRek", dataRekening)

            }
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var binding : FragmentWishlistBinding
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
        // Inflate the layout for this fragment
        binding = FragmentWishlistBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnOrder.setOnClickListener {
            orderInterface?.onClickOrder()
        }
    }


}
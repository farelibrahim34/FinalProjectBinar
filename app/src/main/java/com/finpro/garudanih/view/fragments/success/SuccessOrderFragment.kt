package com.finpro.garudanih.view.fragments.success

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.finpro.garudanih.R
import com.finpro.garudanih.view.fragments.wishlist.WishlistFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuccessOrderFragment : Fragment() {

    companion object {
        const val TAG = "SuccessOrderFragment"

        fun newInstance(dataNama: String, dataRekening : String): SuccessOrderFragment {
            val fragment = SuccessOrderFragment()
            val bundle = Bundle().apply {
                putString("nama", dataNama)
                putString("noRek", dataRekening)

            }
            fragment.arguments = bundle
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_success_order, container, false)
    }

}
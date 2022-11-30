package com.finpro.garudanih.view.fragments.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.finpro.garudanih.R
import com.finpro.garudanih.adapter.AdapterOrder
import com.finpro.garudanih.databinding.FragmentHistoryBinding
import com.finpro.garudanih.model.ListPesawat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private lateinit var binding : FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = arrayListOf(
            ListPesawat("China",2000000,"16 Agustus","24/100",R.drawable.pesawat,"pending"),
            ListPesawat("Malaysia",2000000,"16 Agustus","24/100",R.drawable.pesawat,"pending"),
            ListPesawat("Thailand",2000000,"16 Agustus","24/100",R.drawable.pesawat,"pending"),
            ListPesawat("Singapura",2000000,"16 Agustus","24/100",R.drawable.pesawat,"pending"),
        )
        binding.rvOrder.adapter = AdapterOrder(list)
        binding.rvOrder.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
    }
}
package com.finpro.garudanih.view.fragments.history

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.finpro.garudanih.adapter.AdapterHistory
import com.finpro.garudanih.databinding.FragmentHistoryBinding
import com.finpro.garudanih.viewmodel.AuthViewModel
import com.finpro.garudanih.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {
    lateinit var userViewModel : UserViewModel
    lateinit var authViewModel : AuthViewModel
    lateinit var adapterHistory : AdapterHistory
    private var tokenHistory : String = ""

    companion object {
        const val TAG = "HistoryOrderFragment"

    }

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
        authViewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
        userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        authViewModel.getToken().observe(requireActivity()){token->
            if (token != null){
                tokenHistory = "Bearer "+token
            }
        }
        userViewModel.getHistoryObserve().observe(requireActivity()){
            if (it != null){
                binding.rvHistory.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                adapterHistory = AdapterHistory(it.data.transaction)
                binding.rvHistory.adapter = adapterHistory
                Log.d(TAG, "onViewCreated: ${it.data.transaction}")
                Toast.makeText(context, "Data Tampil", Toast.LENGTH_SHORT).show()
                adapterHistory.notifyDataSetChanged()
            }
        }
        userViewModel.historyUser(tokenHistory)

    }

}
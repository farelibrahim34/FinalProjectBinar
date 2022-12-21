package com.finpro.garudanih.view.wrapper

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.finpro.garudanih.databinding.ItemPage3Binding
import com.finpro.garudanih.utils.CheckUserUtil
import com.finpro.garudanih.view.HomeBottomActivity
import com.finpro.garudanih.view.auth.LoginActivity
import com.finpro.garudanih.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@Suppress("RedundantNullableReturnType", "ClassName", "ReplaceGetOrSet")
@AndroidEntryPoint
class fragment_three : Fragment(){

    private lateinit var binding : ItemPage3Binding
    lateinit var authViewModel : AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemPage3Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authViewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)

        binding.tvLogin.setOnClickListener {
            startActivity(Intent(context, LoginActivity::class.java))
            sudahlogin()
        }
    }
    private fun sudahlogin(){
        authViewModel.getToken().observe(requireActivity()){
            if (it != null){
                val validasi = CheckUserUtil.validateUser(it)
                if (validasi){
                    startActivity(Intent(requireActivity(), HomeBottomActivity::class.java))
                }else{
                    startActivity(Intent(requireActivity(), LoginActivity::class.java))
                }
            }
        }

        authViewModel.getNoHp().observe(requireActivity()){
            if (it != null){
                if (!it.equals("undefined")){
                    startActivity(Intent(requireActivity(), HomeBottomActivity::class.java))
                }else{
                    startActivity(Intent(requireActivity(), LoginActivity::class.java))
                }
            }
        }

    }
}
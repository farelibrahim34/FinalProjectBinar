package com.finpro.garudanih.view.wrapper

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.finpro.garudanih.R
import com.finpro.garudanih.databinding.ItemPage3Binding
import com.finpro.garudanih.view.HomeBottomActivity
import com.finpro.garudanih.view.auth.LoginActivity
import com.finpro.garudanih.view.auth.RegisterActivity

class fragment_three : Fragment(){

    private lateinit var binding : ItemPage3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemPage3Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvLogin.setOnClickListener {
            startActivity(Intent(context, LoginActivity::class.java))

        }
    }
}
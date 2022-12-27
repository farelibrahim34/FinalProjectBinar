package com.finpro.garudanih.view.fragments.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.finpro.garudanih.databinding.FragmentSettingsBinding
import com.finpro.garudanih.view.auth.LoginActivity
import com.finpro.garudanih.view.profile.ProfileActivity
import com.finpro.garudanih.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@Suppress("RedundantNullableReturnType")
@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private lateinit var binding : FragmentSettingsBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]
        logout()

        binding.tvEditProfile.setOnClickListener {
            startActivity(Intent(context, ProfileActivity::class.java))
        }

    }

    private fun logout(){
        binding.btnLogout.setOnClickListener {
            startActivity(Intent(context, LoginActivity::class.java).also {
                authViewModel.apply {
                    deleteToken()
                    deleteData()
                }
            })
            Toast.makeText(context,"Berhasil Logout", Toast.LENGTH_SHORT).show()
        }
    }

}
package com.finpro.garudanih.view.fragments.settings

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.finpro.garudanih.R
import com.finpro.garudanih.databinding.CustomDialogBahasaBinding
import com.finpro.garudanih.databinding.FragmentSettingsBinding
import com.finpro.garudanih.view.auth.LoginActivity
import com.finpro.garudanih.view.profile.ProfileActivity
import com.finpro.garudanih.view.wrapper.SplashScreenActivity
import com.finpro.garudanih.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@Suppress("RedundantNullableReturnType")
@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private lateinit var binding : FragmentSettingsBinding
    private lateinit var authViewModel: AuthViewModel
    lateinit var dialogBinding: CustomDialogBahasaBinding
    var currLang = ""

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
        dialogBinding = CustomDialogBahasaBinding.inflate(layoutInflater)
        logout()

        binding.tvEditProfile.setOnClickListener {
            startActivity(Intent(context, ProfileActivity::class.java))
        }

        binding.tvEditBahasa.setOnClickListener {
            showChangeLanguageDialogue(currLang)
            getLocale()
        }

    }

    private fun setLocale(lang: String) {

        val locale = Locale(lang)
        Locale.setDefault(locale)

        val resources = context?.resources

        val configuration = resources?.configuration
        configuration?.locale = locale
        configuration?.setLayoutDirection(locale)

        resources?.updateConfiguration(configuration, resources.displayMetrics)
    }

    private fun getLocale() {
        val lang = resources.getConfiguration().locale.getLanguage()
        currLang = lang
    }

    private fun showChangeLanguageDialogue(currLang : String){
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.custom_dialog_bahasa)
        dialog.getWindow()!!.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        val btnDeleteYes : Button = dialog.findViewById(R.id.btnConfYes)
        val btnDeleteNo : Button = dialog.findViewById(R.id.btnConfNo)
        if (currLang == "in") {
            btnDeleteYes.setOnClickListener(){
                setLocale("en")
                dialog.dismiss()
                startActivity(Intent(context, SplashScreenActivity::class.java))
            }
            btnDeleteNo.setOnClickListener(){
                dialog.dismiss()
            }
            dialog.show()
        } else if (currLang == "en") {
            btnDeleteYes.setOnClickListener(){
                setLocale("in")
                dialog.dismiss()
                startActivity(Intent(context, SplashScreenActivity::class.java))
            }
            btnDeleteNo.setOnClickListener(){
                dialog.dismiss()
            }
            dialog.show()
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
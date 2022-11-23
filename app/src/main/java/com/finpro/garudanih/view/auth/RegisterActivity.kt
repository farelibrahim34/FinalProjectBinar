package com.finpro.garudanih.view.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< HEAD
import android.view.View
import com.finpro.garudanih.databinding.ActivityRegisterBinding
import android.widget.DatePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.finpro.garudanih.datastore.DataStoreLogin
import com.finpro.garudanih.viewmodel.CobaVmRegister
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
=======
import com.finpro.garudanih.datastore.DataStoreLogin
import com.finpro.garudanih.databinding.ActivityRegisterBinding
>>>>>>> dev-rifqi
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private lateinit var datalogin : DataStoreLogin
    private lateinit var userVM : CobaVmRegister

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userVM = ViewModelProvider(this).get(CobaVmRegister::class.java)


        binding.ivBackDetail.setOnClickListener {
            val toLogin = Intent(this,LoginActivity::class.java)
            startActivity(toLogin)
        }

<<<<<<< HEAD
        binding.btnRegister.setOnClickListener{
//            registerDatastore()
            registerUser()
            val toHome = Intent(this, LoginActivity::class.java)
            startActivity(toHome)
        }


        binding.dummy.text = "--/--/----"

        // create an OnDateSetListener
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
=======
        binding.btnRegister.setOnClickListener{password ->
            if (isPasswordValid) {
                validate()
                binding.passwordInputLayout.error = null
                binding.passwordConfInputLayout.error = null
            } else {
                binding.passwordInputLayout.error = "Character Tidak Sesuai"
                binding.passwordConfInputLayout.error = "Character Tidak Sesuai"
>>>>>>> dev-rifqi
            }
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }

    private fun validate(){
        binding.btnRegister.isEnabled =
            binding.etUsername.text.toString().isNotBlank()
                    && binding.etPassword.text.toString().isNotBlank()
                    && isPasswordValid
    }

<<<<<<< HEAD
//        private fun registerDatastore() {
//            val saveNamaP = binding.etUsername.toString()
//            val saveUsername = binding.etUsername.toString()
//            val saveEmail = binding.etEmail.toString()
//            val saveNoHp = binding.etPhone.toString()
//            val saveTglLahir = binding.tvTgllahir.toString()
//            val saveAlamat = binding.tvAlamat.toString()
//            val savePw = binding.etPassword.toString()
//            val saveUpw = binding.etConfPassword.toString()
//            GlobalScope.launch {
//                datalogin.saveData(saveNamaP,saveUsername,saveEmail,saveNoHp,saveTglLahir,saveAlamat,savePw,saveUpw)
//                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
//        }
//    }

    fun registerUser(){
            val image = binding.ivSetImage.toString()
            val saveNamaP = binding.etUsername.text.toString()
            val saveUsername = binding.etUsername.text.toString()
            val saveEmail = binding.etEmail.text.toString()
            val saveNoHp = binding.etPhone.text.toString()
            val saveTglLahir = binding.tvTgllahir.text.toString().toInt()
            val saveAlamat = binding.tvAlamat.text.toString()
            val savePw = binding.etPassword.text.toString()
            val saveUpw = binding.etConfPassword.text.toString()
        if (saveNamaP.isEmpty()||saveUsername.isEmpty()||saveEmail.isEmpty()||saveNoHp.isEmpty()||saveTglLahir.toString().isEmpty()||saveAlamat.isEmpty()||savePw.isEmpty()){
            Toast.makeText(this, "Please fill all the field", Toast.LENGTH_SHORT).show()
        }else{
            if (savePw == saveUpw){
                userVM.callPostApiUser(saveNamaP,saveEmail,savePw,image,saveNoHp,saveTglLahir)
                userVM.postLiveDataUser().observe(this){
                    if (it != null){
                        Toast.makeText(this, "Registration Success", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }else{
                Toast.makeText(this, "Password not match", Toast.LENGTH_SHORT).show()
            }
        }


=======
    private val isPasswordValid: Boolean get(){
        val passText = binding.etPassword.text.toString()
        return passText.contains("[a-z]".toRegex())
                && passText.contains("[A-Z]".toRegex())
                && passText.contains("[0-9]".toRegex())
                && passText.length >= 8
>>>>>>> dev-rifqi
    }
}
package com.finpro.garudanih.view.auth

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.finpro.garudanih.databinding.ActivityRegisterBinding
import android.widget.DatePicker
import com.finpro.garudanih.datastore.DataStoreLogin
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private lateinit var datalogin : DataStoreLogin

    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.ivBack.setOnClickListener {
            val toLogin = Intent(this,LoginActivity::class.java)
            startActivity(toLogin)
        }

        binding.btnRegister.setOnClickListener{
            registerDatastore()
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
            }
        }
        binding.ivCalender.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(this@RegisterActivity,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }

        })

    }
    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.dummy.text= sdf.format(cal.getTime())
    }

        private fun registerDatastore() {
            val saveNamaP = binding.etUsername.toString()
            val saveUsername = binding.etUsername.toString()
            val saveEmail = binding.etEmail.toString()
            val saveNoHp = binding.etPhone.toString()
            val saveTglLahir = binding.tvTgllahir.toString()
            val saveAlamat = binding.tvAlamat.toString()
            val savePw = binding.etPassword.toString()
            val saveUpw = binding.etConfPassword.toString()
            GlobalScope.launch {
                datalogin.saveData(saveNamaP,saveUsername,saveEmail,saveNoHp,saveTglLahir,saveAlamat,savePw,saveUpw)
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
    }
}
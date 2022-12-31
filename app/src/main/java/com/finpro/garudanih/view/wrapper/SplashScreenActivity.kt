package com.finpro.garudanih.view.wrapper

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.finpro.garudanih.databinding.ActivitySplashScreenBinding
import com.finpro.garudanih.utils.CheckUserUtil
import com.finpro.garudanih.view.HomeBottomActivity
import com.finpro.garudanih.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@Suppress("ReplaceGetOrSet")
@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    lateinit var authViewModel : AuthViewModel
    lateinit var binding : ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)


//        val background = object : Thread() {
//            override fun run() {
//                    Thread.sleep(5000)
////                    val intent = Intent(baseContext, HomeBottomActivity::class.java)
////                    startActivity(intent)
////                    sudahlogin()
////                    finish()
//                    authViewModel.getToken().observe(this@SplashScreenActivity){
//                        if (it != null){
//                            val validasi = CheckUserUtil.validateUser(it)
//                            if (validasi){
//                                startActivity(Intent(baseContext, HomeBottomActivity::class.java))
//                            }else{
//                                startActivity(Intent(baseContext, ViewpagerActivity::class.java))
//                            }
//                        }
//                    }
//                    authViewModel.getNoHp().observe(this@SplashScreenActivity){
//                        if (it != null){
//                            if (!it.equals("undefined")){
//                                startActivity(Intent(baseContext, HomeBottomActivity::class.java))
//                            }else{
//                                startActivity(Intent(baseContext, ViewpagerActivity::class.java))
//                            }
//                        }
//                    }
//
//
//            }
//        }
//        background.start()

        Handler().postDelayed({
            sudahlogin()
//            finish()
        },5000)
    }
    private fun sudahlogin(){


        authViewModel.getToken().observe(this){
            if (it != null){
                val validasi = CheckUserUtil.validateUser(it)
                if (validasi){
                    startActivity(Intent(this, HomeBottomActivity::class.java))
                }else{
                    startActivity(Intent(this, ViewpagerActivity::class.java))
                }
            }
        }

        authViewModel.getNoHp().observe(this){
            if (it != null){
                if (!it.equals("undefined")){
                    startActivity(Intent(this, HomeBottomActivity::class.java))
                }else{
                    startActivity(Intent(this, ViewpagerActivity::class.java))
                }
            }
        }
        finish()

    }
}
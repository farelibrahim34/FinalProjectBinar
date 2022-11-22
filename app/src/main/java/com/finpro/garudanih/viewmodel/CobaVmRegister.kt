package com.finpro.garudanih.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.finpro.garudanih.model.Data
import com.finpro.garudanih.model.DataUser
import com.finpro.garudanih.network.ApiClient
import retrofit2.Call
import retrofit2.Response
import java.util.*

class CobaVmRegister: ViewModel() {
    lateinit var postLDUser : MutableLiveData<Data?>
    init {
        postLDUser = MutableLiveData()
    }
    fun postLiveDataUser(): MutableLiveData<Data?> {
        return postLDUser
    }
    fun callPostApiUser(name : String,
                        email : String,
                        password : String,
                        image : String,
                        phone : String,
                        birth : Int
    ){
        ApiClient.instance.registerUser(
            DataUser(name,email,password,image,phone,birth))
            .enqueue(object : retrofit2.Callback<Data>{
                override fun onResponse(call: Call<Data>, response: Response<Data>) {
                    if (response.isSuccessful){
                        postLDUser.postValue(response.body())
                    }else{
                        Log.d("Error", response.message())
                        postLDUser.postValue(null)

                    }
                }

                override fun onFailure(call: Call<Data>, t: Throwable) {
                    postLDUser.postValue(null)
                }

            })

    }
}
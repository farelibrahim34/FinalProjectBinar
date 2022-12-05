package com.finpro.garudanih.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.finpro.garudanih.model.ResponseUserCurrent
import com.finpro.garudanih.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DataUserRepository @Inject constructor(private val api: ApiInterface) {

    private val getCurrentUser : MutableLiveData<ResponseUserCurrent?> = MutableLiveData()

    fun getCurrentUserObserve(): MutableLiveData<ResponseUserCurrent?> = getCurrentUser

    fun getDataUser(token:String){
        api.getUserLogin(token)
            .enqueue(object : Callback<ResponseUserCurrent>{
                override fun onResponse(
                    call: Call<ResponseUserCurrent>,
                    response: Response<ResponseUserCurrent>
                ) {
                    if (response.isSuccessful){
                        val body = response.body()
                        if (body != null){
                            getCurrentUser.postValue(body)
                        }else{
                            getCurrentUser.postValue(null)
                            Log.d("CURRENT_USER","Null")
                        }
                    }else{
                        getCurrentUser.postValue(null)
                        Log.d("CURRENT_USER",response.message())
                    }
                }

                override fun onFailure(call: Call<ResponseUserCurrent>, t: Throwable) {
                    getCurrentUser.postValue(null)
                    Log.d("CURRENT_USER","onFailure")
                }

            })
    }

}
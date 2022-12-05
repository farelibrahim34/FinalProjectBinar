package com.finpro.garudanih.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.finpro.garudanih.model.DataProfile
import com.finpro.garudanih.model.ResponseUserCurrent
import com.finpro.garudanih.model.ResponseUserUpdate
import com.finpro.garudanih.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DataUserRepository @Inject constructor(private val api: ApiInterface) {

    private val getCurrentUser : MutableLiveData<ResponseUserCurrent?> = MutableLiveData()
    private val updateCurrentUser : MutableLiveData<ResponseUserUpdate?> = MutableLiveData()

    fun getCurrentUserObserve(): MutableLiveData<ResponseUserCurrent?> = getCurrentUser
    fun updateCurrentUserObserve() : MutableLiveData<ResponseUserUpdate?> = updateCurrentUser

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
    fun updateCurrentUser(token : String, nomor : String, tanggallahir : String, kota : String){
        api.updateUserLogin(token, DataProfile(nomor,tanggallahir,kota))
            .enqueue(object : Callback<ResponseUserUpdate>{
                override fun onResponse(
                    call: Call<ResponseUserUpdate>,
                    response: Response<ResponseUserUpdate>
                ) {
                    if(response.isSuccessful){
                        val body = response.body()
                        if(body != null){
                            updateCurrentUser.postValue(body)
                        }else{
                            updateCurrentUser.postValue(null)
                            Log.d("CURRENT_USER","Null")
                        }
                    }else{
                        updateCurrentUser.postValue(null)
                        Log.d("CURRENT_USER",response.message())
                    }
                }

                override fun onFailure(call: Call<ResponseUserUpdate>, t: Throwable) {
                    updateCurrentUser.postValue(null)
                    Log.d("CURRENT_USER","onFailure")
                }

            })
    }

}
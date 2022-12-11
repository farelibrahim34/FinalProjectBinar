package com.finpro.garudanih.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.finpro.garudanih.model.Data
import com.finpro.garudanih.model.DataClassUser
import com.finpro.garudanih.model.DataUserResponse
import com.finpro.garudanih.model.Ticket
import com.finpro.garudanih.network.ApiClient
import com.finpro.garudanih.network.ApiInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
@HiltViewModel
class ViewModelUser @Inject constructor(private val api : ApiInterface): ViewModel() {

    lateinit var postLdUser : MutableLiveData<DataUserResponse?>



    init {
        postLdUser = MutableLiveData()
    }

    fun postLiveDataUser(): MutableLiveData<DataUserResponse?> {
        return postLdUser
    }


    fun callPostApiUser(name : String, email : String, password : String){
        api.registerUser(DataClassUser(name,email,password))
            .enqueue(object :Callback<DataUserResponse>{
                override fun onResponse(
                    call: Call<DataUserResponse>,
                    response: Response<DataUserResponse>
                ) {
                    if (response.isSuccessful){
                        postLdUser.postValue(response.body())
                    }else{
                        Log.d("Error", response.message())
                        postLdUser.postValue(null)
                    }
                }

                override fun onFailure(call: Call<DataUserResponse>, t: Throwable) {
                    postLdUser.postValue(null)
                }
            })
    }

}
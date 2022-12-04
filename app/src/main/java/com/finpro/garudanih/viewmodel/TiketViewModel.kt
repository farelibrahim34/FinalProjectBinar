package com.finpro.garudanih.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.finpro.garudanih.model.ResponseDetailTiket
import com.finpro.garudanih.model.ResponseListTiket
import com.finpro.garudanih.model.Ticket
import com.finpro.garudanih.network.ApiInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TiketViewModel @Inject constructor(var api : ApiInterface):ViewModel() {





    var ldListTiket : MutableLiveData<ResponseListTiket?>
    var postTiket : MutableLiveData<ResponseListTiket?>



    init {
        ldListTiket = MutableLiveData()
        postTiket = MutableLiveData()
    }


    fun getLdTiket(): MutableLiveData<ResponseListTiket?> {
        return  ldListTiket
    }
    fun postTiket(): MutableLiveData<ResponseListTiket?>{
        return postTiket

    }

    fun CallApiTiket(){
        api.getAllListTicket()
            .enqueue(object : Callback<ResponseListTiket>{
                override fun onResponse(
                    call: Call<ResponseListTiket>,
                    response: Response<ResponseListTiket>
                ) {
                    if (response.isSuccessful){
                        ldListTiket.postValue(response.body())
                        Log.d("data",response.body()?.data.toString())
                    }else{
                        Log.d("data",response.body()?.data.toString())
                    }
                }


                override fun onFailure(call: Call<ResponseListTiket>, t: Throwable) {
                    ldListTiket.postValue(null)
                }

            })
            }}

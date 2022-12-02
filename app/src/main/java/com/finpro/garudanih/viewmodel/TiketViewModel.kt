package com.finpro.garudanih.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.finpro.garudanih.model.DataX
import com.finpro.garudanih.model.Ticket
import com.finpro.garudanih.network.ApiInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TiketViewModel @Inject constructor(var api : ApiInterface):ViewModel() {

    var ldListTiket : MutableLiveData<DataX>
    var postTiket : MutableLiveData<DataX>


    init {
        ldListTiket = MutableLiveData()
        postTiket = MutableLiveData()

    }

    fun getLdTiket(): MutableLiveData<DataX> {
        return  ldListTiket
    }
    fun postTiket(): MutableLiveData<DataX>{
        return postTiket
    }

    fun CallApiTiket(){
        api.getAllListTicket()
            .enqueue(object : Callback<DataX>{
                override fun onResponse(
                    call: Call<DataX>,
                    response: Response<DataX>
                ) {
                    if (response.isSuccessful){
                        ldListTiket.postValue(response.body())
                        Log.d("data",response.body()?.tickets.toString())
                    }else{
                        Log.d("data",response.body()?.tickets.toString())
                    }
                }
                override fun onFailure(call: Call<DataX>, t: Throwable) {
                    Log.d("data",call.toString())
                }

            })
    }

}
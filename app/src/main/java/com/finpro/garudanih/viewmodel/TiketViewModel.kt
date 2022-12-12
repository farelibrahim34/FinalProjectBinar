package com.finpro.garudanih.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.finpro.garudanih.model.*
import com.finpro.garudanih.network.ApiInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TiketViewModel @Inject constructor(var api : ApiInterface):ViewModel() {


    lateinit var ldListTiket : MutableLiveData<ResponseListTiket?>
    lateinit var postTiket : MutableLiveData<ResponseListTiket?>
    lateinit var ldListTiketIntr : MutableLiveData<ResponseListTiket?>


    init {
        ldListTiket = MutableLiveData()
        postTiket = MutableLiveData()
        ldListTiketIntr = MutableLiveData()

    }


    fun getLdTiket(): MutableLiveData<ResponseListTiket?> {
        return  ldListTiket
    }
    fun postTiket(): MutableLiveData<ResponseListTiket?>{
        return postTiket
    }
    fun getLdTiketIntr(): MutableLiveData<ResponseListTiket?>{
        return ldListTiketIntr
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
    }

    fun callApiTiketIntr(){
        api.getAllListTicketIntr()
            .enqueue(object : Callback<ResponseListTiket>{
                override fun onResponse(
                    call: Call<ResponseListTiket>,
                    response: Response<ResponseListTiket>
                ) {
                    if (response.isSuccessful){
                        ldListTiketIntr.postValue(response.body())
                    }else{
                        ldListTiketIntr.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseListTiket>, t: Throwable) {
                    ldListTiketIntr.postValue(null)
                }

            })
    }

}

package com.finpro.garudanih.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.finpro.garudanih.model.ResponseListTiket
import com.finpro.garudanih.model.Ticket
import com.finpro.garudanih.network.ApiInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TiketViewModel @Inject constructor(private val api : ApiInterface):ViewModel() {

    lateinit var ldListTiket : MutableLiveData<ResponseListTiket?>


    init {
        ldListTiket = MutableLiveData()

    }

    fun getAllTiket(): MutableLiveData<ResponseListTiket?> {
        return  ldListTiket
    }

    fun callApiListTiket(){
        api.getAllListTicket()
            .enqueue(object : Callback<ResponseListTiket>{
                override fun onResponse(
                    call: Call<ResponseListTiket>,
                    response: Response<ResponseListTiket>
                ) {
                    if (response.isSuccessful){
                        ldListTiket.postValue(response.body())
                        Log.d("response",response.body().toString())
                    }else{
                        ldListTiket.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseListTiket>, t: Throwable) {
                    ldListTiket.postValue(null)
                }
            })
    }

}
package com.finpro.garudanih.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.finpro.garudanih.model.Ticket
import com.finpro.garudanih.network.ApiInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TiketViewModel @Inject constructor(private val api : ApiInterface):ViewModel() {

    lateinit var ldListTiket : MutableLiveData<List<Ticket>?>


    init {
        ldListTiket = MutableLiveData()

    }

    fun getAllTiket(): MutableLiveData<List<Ticket>?> {
        return  ldListTiket
    }

    fun callApiListTiket(){
        api.getAllListTicket()
            .enqueue(object : Callback<List<Ticket>>{
                override fun onResponse(
                    call: Call<List<Ticket>>,
                    response: Response<List<Ticket>>
                ) {
                    if (response.isSuccessful){
                        ldListTiket.postValue(response.body())
                        Log.d("response",response.body().toString())
                    }else{
                        ldListTiket.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<Ticket>>, t: Throwable) {
                    ldListTiket.postValue(null)
                }
            })
    }

}
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
//    lateinit var postOrderTiket : MutableLiveData<ResponseOrder?>

    init {
        ldListTiket = MutableLiveData()
        postTiket = MutableLiveData()
//        postOrderTiket = MutableLiveData()
    }


    fun getLdTiket(): MutableLiveData<ResponseListTiket?> {
        return  ldListTiket
    }
    fun postTiket(): MutableLiveData<ResponseListTiket?>{
        return postTiket
    }
//    fun postTiketOrder(): MutableLiveData<ResponseOrder?> {
//        return postOrderTiket
//    }

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
//    fun callPostOrder(ticketId:Int,orderBy : String,ktp: String,numChair : Int){
//        api.orderTiket(ticketId,DataOrder(orderBy,ktp,numChair))
//            .enqueue(object : Callback<ResponseOrder>{
//                override fun onResponse(
//                    call: Call<ResponseOrder>,
//                    response: Response<ResponseOrder>
//                ) {
//                    if (response.isSuccessful){
//                        postOrderTiket.postValue(response.body())
//                        Log.d("data",response.body()?.data.toString())
//                    }else{
//                        postOrderTiket.postValue(null)
//                        Log.d("data",response.body()?.data.toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<ResponseOrder>, t: Throwable) {
//                    postOrderTiket.postValue(null)
//                }
//
//            })
//    }
}

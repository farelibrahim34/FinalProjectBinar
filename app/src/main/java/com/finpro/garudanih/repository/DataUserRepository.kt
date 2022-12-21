package com.finpro.garudanih.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.finpro.garudanih.model.*
import com.finpro.garudanih.model.updatepaid.ResponsePaid
import com.finpro.garudanih.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DataUserRepository @Inject constructor(private val api: ApiInterface) {

    private val getCurrentUser : MutableLiveData<ResponseUserCurrent?> = MutableLiveData()
    private val updateCurrentUser : MutableLiveData<ResponseUserUpdate?> = MutableLiveData()
    private val postOrder : MutableLiveData<ResponseOrder?> = MutableLiveData()

    private val getHistoryUser: MutableLiveData<HistoryResponse?> = MutableLiveData()
    private val putPaidUser : MutableLiveData<ResponsePaid?> = MutableLiveData()

    fun getCurrentUserObserve(): MutableLiveData<ResponseUserCurrent?> = getCurrentUser
    fun updateCurrentUserObserve() : MutableLiveData<ResponseUserUpdate?> = updateCurrentUser
    fun postOrderObserve(): MutableLiveData<ResponseOrder?> = postOrder

    fun getHistoryObserve(): MutableLiveData<HistoryResponse?> = getHistoryUser
    fun putPaidUserObserve() : MutableLiveData<ResponsePaid?> = putPaidUser

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
        api.updateUserLogin(token, UpdateProfile(nomor,tanggallahir,kota))
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
    fun postOrderTiket(token : String,ticketId:Int,orderBy : String,ktp: String,numChair : Int){
        api.orderTiket(token,ticketId, DataOrder(orderBy,ktp,numChair))
            .enqueue(object : Callback<ResponseOrder>{
                override fun onResponse(
                    call: Call<ResponseOrder>,
                    response: Response<ResponseOrder>
                ) {
                    if (response.isSuccessful){
                        val body = response.body()
                        if (body != null){
                            postOrder.postValue(body)
                        }else{
                            postOrder.postValue(null)
                            Log.d("ORDER_TIKET","Null")
                        }
                    }else{
                        postOrder.postValue(null)
                        Log.d("ORDER_TIKET",response.message())
                    }
                }

                override fun onFailure(call: Call<ResponseOrder>, t: Throwable) {
                    postOrder.postValue(null)
                    Log.d("ORDER_TIKET","onFailure")
                }

            })
    }

    fun getHistory(token : String){
        api.getHistoryPemesanan(token)
            .enqueue(object  : Callback<HistoryResponse>{
                override fun onResponse(
                    call: Call<HistoryResponse>,
                    response: Response<HistoryResponse>
                ) {
                    if (response.isSuccessful){
                        val body = response.body()
                        if (body != null){
                            getHistoryUser.postValue(body)
                        }else{
                            getHistoryUser.value = null
                            Log.d("HISTORY","Null")
                        }
                    }else{
                        getHistoryUser.value = null
                        Log.d("HISTORY",response.message())
                    }
                }

                override fun onFailure(call: Call<HistoryResponse>, t: Throwable) {
                    getHistoryUser.postValue(null)
                    Log.d("HISTORY","onFailure")
                }

            })
    }
    fun callUpdatePaid(transId: Int){
        api.updatePaid(transId)
            .enqueue(object : Callback<ResponsePaid>{
                override fun onResponse(
                    call: Call<ResponsePaid>,
                    response: Response<ResponsePaid>
                ) {
                    if (response.isSuccessful){
                        putPaidUser.postValue(response.body())
                    }else{
                        putPaidUser.postValue(null)
                        Log.d("PAID",response.message())
                    }
                }

                override fun onFailure(call: Call<ResponsePaid>, t: Throwable) {
                    putPaidUser.postValue(null)
                    Log.d("PAID","onFailure")
                }

            })

    }

}
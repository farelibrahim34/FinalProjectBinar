package com.finpro.garudanih.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.finpro.garudanih.model.*
import com.finpro.garudanih.model.order.DataOrderPP
import com.finpro.garudanih.model.responsedetail.ResponseDetailTiket
import com.finpro.garudanih.network.ApiInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@Suppress("JoinDeclarationAndAssignment", "unused")
@HiltViewModel
class ViewModelUser @Inject constructor(private val api : ApiInterface): ViewModel() {

    lateinit var postLdUser : MutableLiveData<DataUserResponse?>
    lateinit var editProfileUser : MutableLiveData<ResponseUserUpdate?>
    lateinit var tiketPP : MutableLiveData<ResponseDetailTiket?>
    lateinit var getDetailByid : MutableLiveData<ResponseDetailTiket?>
    lateinit var getIdPergi:MutableLiveData<ResponseDetailTiket?>


    init {
        postLdUser = MutableLiveData()
        editProfileUser = MutableLiveData()
        tiketPP = MutableLiveData()
        getDetailByid = MutableLiveData()
        getIdPergi = MutableLiveData()
    }

    fun getIdPergiObserve(token: String,id:Int):MutableLiveData<ResponseDetailTiket?>{
        return getIdPergi
    }

    fun getDetailByIdObserve():MutableLiveData<ResponseDetailTiket?>{
        return getDetailByid
    }

    fun tiketPPObserve():MutableLiveData<ResponseDetailTiket?>{
        return tiketPP
    }

    fun postLiveDataUser(): MutableLiveData<DataUserResponse?> {
        return postLdUser
    }
    fun editProfileUserObserve(): MutableLiveData<ResponseUserUpdate?>{
        return editProfileUser
    }

    fun callIdPergi(token: String,id:Int){
        api.getDetailByid(token,id)
            .enqueue(object : Callback<ResponseDetailTiket>{
                override fun onResponse(
                    call: Call<ResponseDetailTiket>,
                    response: Response<ResponseDetailTiket>
                ) {
                    if (response.isSuccessful){
                        getIdPergi.postValue(response.body())
                    }else{
                        getIdPergi.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseDetailTiket>, t: Throwable) {
                    getIdPergi.postValue(null)
                }

            })
    }
    fun callDetailById(token: String,id:Int){
        api.getDetailByid(token,id)
            .enqueue(object :Callback<ResponseDetailTiket>{
                override fun onResponse(
                    call: Call<ResponseDetailTiket>,
                    response: Response<ResponseDetailTiket>
                ) {
                    if (response.isSuccessful){
                        getDetailByid.postValue(response.body())
                    }else{
                        getDetailByid.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseDetailTiket>, t: Throwable) {
                    getDetailByid.postValue(null)
                }

            })
    }

    fun callTiketPP(token:String,
                    id:Int,
                    orderBy:String,
                    ktp:String,
                    numChair:Int,
                    returnTicketId:Int,
                    returnTicketChair:Int){
        api.orderTiketPP(token,id,DataOrderPP(orderBy,ktp,numChair,returnTicketId, returnTicketChair))
            .enqueue(object :Callback<ResponseDetailTiket>{
                override fun onResponse(
                    call: Call<ResponseDetailTiket>,
                    response: Response<ResponseDetailTiket>
                ) {
                    if (response.isSuccessful){
                        tiketPP.postValue(response.body())
                    }else{
                        tiketPP.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseDetailTiket>, t: Throwable) {
                    tiketPP.postValue(null)
                }

            })
    }

    fun callEditProfile(token: String,name:RequestBody,fileImage : MultipartBody.Part,phone:RequestBody,birth:RequestBody,city:RequestBody){
        api.editProfile(token,name,fileImage,phone,birth,city)
            .enqueue(object : Callback<ResponseUserUpdate>{
                override fun onResponse(
                    call: Call<ResponseUserUpdate>,
                    response: Response<ResponseUserUpdate>
                ) {
                    if (response.isSuccessful){
                        editProfileUser.postValue(response.body())
                    }else{
                        editProfileUser.postValue(null)
                    }
                }
                override fun onFailure(call: Call<ResponseUserUpdate>, t: Throwable) {
                    editProfileUser.postValue(null)
                }

            })
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
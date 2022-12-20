package com.finpro.garudanih.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.finpro.garudanih.model.DataClassUser
import com.finpro.garudanih.model.DataUserResponse
import com.finpro.garudanih.model.ResponseUserUpdate
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
    init {
        postLdUser = MutableLiveData()
        editProfileUser = MutableLiveData()
    }

    fun postLiveDataUser(): MutableLiveData<DataUserResponse?> {
        return postLdUser
    }
    fun editProfileUserObserve(): MutableLiveData<ResponseUserUpdate?>{
        return editProfileUser
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
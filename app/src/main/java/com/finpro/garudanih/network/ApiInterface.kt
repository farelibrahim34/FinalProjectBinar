package com.finpro.garudanih.network

import com.finpro.garudanih.model.Data
import com.finpro.garudanih.model.DataUser
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiInterface {
    @GET("api/user/register")
    fun registerUser(@Body request : DataUser): Call<Data>

}
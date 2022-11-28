package com.finpro.garudanih.network

import com.finpro.garudanih.model.Data
import com.finpro.garudanih.model.DataClassUser
import com.finpro.garudanih.model.DataUserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("api/user/register")
    fun registerUser(@Body request : DataClassUser): Call<DataUserResponse>
}
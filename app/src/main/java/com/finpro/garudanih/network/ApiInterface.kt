package com.finpro.garudanih.network

import com.finpro.garudanih.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @POST("v1/user/register")
    fun registerUser(@Body request : DataClassUser): Call<DataUserResponse>

    @POST("v1/user/login")
    fun loginUser(@Body userLogin : UserLogin): Call<ResponseUserLogin>

    @GET("v1/user/current")
    fun getUserLogin(@Header("Authorization")authHeader : String): Call<ResponseUserCurrent>


//    @PUT("api/user/update")
//    fun updateUserLogin(@Header("Authorization")auth: String,
//                        @Body user : UpdateProfile):Call<ResponseUserUpdate>
}
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

    @GET("v1/ticket-doms")
    fun getAllListTicket() : Call<ResponseListTiket>

    @GET("v1/ticket-intr")
    fun getAllListTicketIntr() : Call<ResponseListTiket>

    @GET("v1/ticket/{id}")
    fun getTiketByid(@Path("id") id : Int): Call<ResponseDetailTiket>

    @POST("v1/trans/{ticketId}")
    fun orderTiket(@Header("Authorization")auth : String,
                   @Path("ticketId") ticketId : Int,
                   @Body request: DataOrder): Call<ResponseOrder>

    @GET("v1/user/history")
    fun getHistoryPemesanan(@Header("Authorization")authHeader : String) : Call<HistoryResponse>

}
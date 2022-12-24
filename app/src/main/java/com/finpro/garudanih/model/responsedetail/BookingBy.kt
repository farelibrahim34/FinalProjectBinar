package com.finpro.garudanih.model.responsedetail


import com.google.gson.annotations.SerializedName

data class BookingBy(
    @SerializedName("numChair")
    val numChair: Int,
    @SerializedName("userId")
    val userId: Int
)
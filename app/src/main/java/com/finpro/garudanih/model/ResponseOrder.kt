package com.finpro.garudanih.model


import com.google.gson.annotations.SerializedName

data class ResponseOrder(
    @SerializedName("data")
    val `data`: DataXXX,
    @SerializedName("status")
    val status: String
)
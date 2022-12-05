package com.finpro.garudanih.model


import com.google.gson.annotations.SerializedName

data class ResponseDetailTiket(
    @SerializedName("data")
    val `data`: DataXX,
    @SerializedName("status")
    val status: String
)
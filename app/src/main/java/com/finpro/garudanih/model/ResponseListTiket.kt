package com.finpro.garudanih.model


import com.google.gson.annotations.SerializedName

data class ResponseListTiket(
    @SerializedName("data")
    val `data`: DataX,
    @SerializedName("status")
    val status: String
)
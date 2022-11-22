package com.finpro.garudanih.model


import com.google.gson.annotations.SerializedName

data class CobaRegisterResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("status")
    val status: String
)
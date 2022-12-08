package com.finpro.garudanih.model


import com.google.gson.annotations.SerializedName

data class HistoryResponse(
    @SerializedName("data")
    val `data`: DataXXXXX,
    @SerializedName("status")
    val status: String
)
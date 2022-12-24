package com.finpro.garudanih.model.responsedetail


import com.google.gson.annotations.SerializedName

data class ResponseDetailTiket(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("status")
    val status: String
)
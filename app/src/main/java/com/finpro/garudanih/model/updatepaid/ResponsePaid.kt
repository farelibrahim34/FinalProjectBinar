package com.finpro.garudanih.model.updatepaid


import com.google.gson.annotations.SerializedName

data class ResponsePaid(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("status")
    val status: String
)
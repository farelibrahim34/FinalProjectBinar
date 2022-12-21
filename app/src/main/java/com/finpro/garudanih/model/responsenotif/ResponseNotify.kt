package com.finpro.garudanih.model.responsenotif


import com.google.gson.annotations.SerializedName

@Suppress("unused")
data class ResponseNotify(
    @SerializedName("data")
    val `data`: List<DataNotify>,
    @SerializedName("status")
    val status: String
)
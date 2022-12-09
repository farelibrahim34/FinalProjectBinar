package com.finpro.garudanih.model


import com.google.gson.annotations.SerializedName

data class DataXXXXX(
    @SerializedName("transaction")
    val transaction: List<Transaction>
)
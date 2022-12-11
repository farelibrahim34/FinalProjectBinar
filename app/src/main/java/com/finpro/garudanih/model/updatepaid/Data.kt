package com.finpro.garudanih.model.updatepaid


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("desc")
    val desc: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isRead")
    val isRead: Boolean,
    @SerializedName("type")
    val type: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("userId")
    val userId: Int
)
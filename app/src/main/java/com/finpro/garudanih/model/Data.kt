package com.finpro.garudanih.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("birth")
    val birth: Any,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("deletedAt")
    val deletedAt: Any,
    @SerializedName("email")
    val email: String,
    @SerializedName("exist")
    val exist: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: Any,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phone")
    val phone: Any,
    @SerializedName("role")
    val role: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)
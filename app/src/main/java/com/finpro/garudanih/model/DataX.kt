package com.finpro.garudanih.model


import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("tickets")
    val tickets: List<Ticket>
)
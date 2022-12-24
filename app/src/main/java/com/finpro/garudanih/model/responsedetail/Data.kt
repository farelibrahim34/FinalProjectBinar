package com.finpro.garudanih.model.responsedetail


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("returnTicket")
    val returnTicket: List<ReturnTicket>,
    @SerializedName("ticket")
    val ticket: Ticket
)
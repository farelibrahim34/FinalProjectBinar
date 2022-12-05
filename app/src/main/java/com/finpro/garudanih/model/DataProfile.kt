package com.finpro.garudanih.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
class DataProfile (
    val nomor: String,
    val tanggallahir: String,
    val kota: String
    ):Parcelable

data class ResponseUserUpdate(
    val message : String
)
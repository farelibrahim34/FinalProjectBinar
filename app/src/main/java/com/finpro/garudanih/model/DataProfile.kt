package com.finpro.garudanih.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class DataProfile (
    val nomor: String,
    val tanggallahir: String,
    val kota: String
    ):Parcelable


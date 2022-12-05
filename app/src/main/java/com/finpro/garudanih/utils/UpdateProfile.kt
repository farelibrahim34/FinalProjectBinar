package com.finpro.garudanih.utils

object UpdateProfile {
    fun validateEditProfile(nomor: String,tanggallahir: String,kota: String): String{
        if(nomor.isBlank() || tanggallahir.isBlank() || kota.isBlank()){
            return "Field cannot be empety"
        }
        return "success"

    }
}
package com.finpro.garudanih.utils

object UpdateProfile {
    fun validateEditProfile(nomor: String,tanggallahir: String, city : String): String{
        if(nomor.isBlank() || tanggallahir.isBlank() || city.isBlank()){
            return "Field cannot be empety"
        }
        return "success"

    }
}
package com.finpro.garudanih.utils

object UpdateProfile {
    fun validateEditProfile(nomor: String,tanggallahir: String,image: String): String{
        if(nomor.isBlank() || tanggallahir.isBlank() || image.isBlank()){
            return "Field cannot be empety"
        }
        return "success"

    }
}
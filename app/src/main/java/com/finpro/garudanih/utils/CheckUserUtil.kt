package com.finpro.garudanih.utils

object CheckUserUtil {
    fun validateUser(token : String): Boolean{
        if(token.equals("undefined")){
            return false
        }
        return true

    }
}
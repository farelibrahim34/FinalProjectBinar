package com.finpro.garudanih.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//LOGIN
data class UserLogin (
    val email : String,
    val password : String
)

data class ResponseUserLogin(
    val token : String
)
// current
data class ResponseUserCurrent(
    val id : Int,
    val name : String,
    val email : String,
    val password : String,
    val image : String,
    val phone : String,
    val birth : String,
    val role : String,
    val isExist : Boolean,
    val isVerify : String,
    val deleteAt : String,
    val createdAt : String,
    val updatedAt : String,
    val city : String
)
//update profile
@Parcelize
data class UpdateProfile(
    val phone : String,
    val birth : String,
    val city : String,
    //val image: String
): Parcelable

data class ResponseUserUpdate(
    val message : String
)

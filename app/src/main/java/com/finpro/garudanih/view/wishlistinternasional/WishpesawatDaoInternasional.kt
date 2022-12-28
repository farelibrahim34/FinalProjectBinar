package com.finpro.garudanih.view.wishlistinternasional

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface WishpesawatDaoInternasional {

    @Insert
    fun addToWishListInternasional(dataWishPesawatInternasional: dataWishPesawatInternasional):Long

    @Query("SELECT * FROM dataWishPesawatInternasional")
    fun getWishPesawatInternasional() : List<dataWishPesawatInternasional>

    //
    @Query("SELECT count(*) FROM dataWishPesawatInternasional WHERE dataWishPesawatInternasional.id = :id")
    fun checkWishInternasioal(id: Int) : Int

    @Delete
    fun deleteWishInter(dataWishPesawatInternasional: dataWishPesawatInternasional) : Int
}
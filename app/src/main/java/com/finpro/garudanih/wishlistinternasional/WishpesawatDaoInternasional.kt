package com.finpro.garudanih.wishlistinternasional

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.finpro.garudanih.wishlist.DataWishPesawatLoc


@Dao
interface WishpesawatDaoInternasional {

    @Insert
    fun addToWishListInternasional(dataWishPesawatInternasional: dataWishPesawatInternasional):Long

    @Query("SELECT * FROM dataWishPesawatInternasional")
    fun getWishPesawatInternasional() : List<dataWishPesawatInternasional>

    //
    @Query("SELECT count(*) FROM dataWishPesawatInternasional WHERE dataWishPesawatInternasional.id = :id")
    fun checkWishInternasioal(id: Int) : Int

}
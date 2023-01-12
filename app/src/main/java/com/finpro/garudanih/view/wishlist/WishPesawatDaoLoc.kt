package com.finpro.garudanih.view.wishlist

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface WishPesawatDaoLoc {

    @Insert
    fun addToWishList(dataWishPesawat: DataWishPesawatLoc):Long

    @Query("SELECT * FROM DataWishPesawatLoc")
    fun getWishPesawat() : List<DataWishPesawatLoc>


    @Query("SELECT count(*) FROM DataWishPesawatLoc WHERE DataWishPesawatLoc.id = :id")
    fun checkWish(id: Int) : Int

    @Delete
    fun deleteWishLoc(dataWishPesawat: DataWishPesawatLoc) : Int
}
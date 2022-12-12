package com.finpro.garudanih.wishlist

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dagger.hilt.android.internal.lifecycle.HiltViewModelMap



@Dao
interface WishPesawatDaoLoc {

    @Insert
    fun addToWishList(dataWishPesawat: DataWishPesawatLoc):Long

    @Query("SELECT * FROM DataWishPesawatLoc")
    fun getWishPesawat() : List<DataWishPesawatLoc>

    //
    @Query("SELECT count(*) FROM DataWishPesawatLoc WHERE DataWishPesawatLoc.id = :id")
    fun checkWish(id: Int) : Int
}
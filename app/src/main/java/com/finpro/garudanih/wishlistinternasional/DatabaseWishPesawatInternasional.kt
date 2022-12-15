package com.finpro.garudanih.wishlistinternasional

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.finpro.garudanih.wishlist.DataWishPesawatLoc
import com.finpro.garudanih.wishlist.DatabaseWishPesawatLoc
import com.finpro.garudanih.wishlist.WishPesawatDaoLoc


@Database(entities = [dataWishPesawatInternasional::class], version = 1)
abstract class DatabaseWishPesawatInternasional : RoomDatabase() {
    abstract fun WishInternasionalDao() : WishpesawatDaoInternasional

    companion object{
        private var INSTANCE : DatabaseWishPesawatInternasional? = null

        fun getInstance(context : Context): DatabaseWishPesawatInternasional? {
            if (INSTANCE == null){
                synchronized(DatabaseWishPesawatInternasional::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        DatabaseWishPesawatInternasional::class.java,"wishlistinternasional.db").build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}
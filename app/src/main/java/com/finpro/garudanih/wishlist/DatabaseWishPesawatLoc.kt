package com.finpro.garudanih.wishlist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.hilt.android.internal.lifecycle.HiltViewModelMap


@Database(entities = [DataWishPesawatLoc::class], version = 1)
abstract class DatabaseWishPesawatLoc : RoomDatabase(){
    abstract fun WishDao() : WishPesawatDaoLoc

    companion object{
        private var INSTANCE : DatabaseWishPesawatLoc? = null

        fun getInstance(context : Context):DatabaseWishPesawatLoc? {
            if (INSTANCE == null){
                synchronized(DatabaseWishPesawatLoc::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        DatabaseWishPesawatLoc::class.java,"wishlist.db").build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}
package com.finpro.garudanih.view.wishlistinternasional

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Suppress("FunctionName", "unused")
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
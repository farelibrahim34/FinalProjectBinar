package com.finpro.garudanih.wishlistinternasional

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity
@Parcelize
data class dataWishPesawatInternasional(
    @PrimaryKey
    val id : Int?,
    @ColumnInfo(name = "destination")
    var destination : String,
    @ColumnInfo(name = "departure")
    var departure : String,
    @ColumnInfo(name = "takeOff")
    var takeOff : String,
    @ColumnInfo(name = "price")
    var price : Int,
    @ColumnInfo(name = "totalChair")
    var totalChair : Int,
    @ColumnInfo(name = "classX")
    var classX : String
) : Parcelable

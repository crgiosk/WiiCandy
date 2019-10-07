package com.wiedii.wiicandy.DAO

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Compra(
    //@PrimaryKey  val id:Int?,
    @ColumnInfo(name = "usuario") val usuario: String,
    @ColumnInfo(name = "producto") val producto:String,
    @ColumnInfo(name = "cantidad") val cantidad:String,
    @ColumnInfo(name = "fecha") val fecha:String

)
package com.wiedii.wiicandy.DAO

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wiedii.wiicandy.Helpers.Utilidades


@Entity(tableName = "compra_tabla")



//@Entity(tableName = Utilidades.tes)
/**
 * Clase que define las columnas de la base de datos
 */
class Compra(
    @PrimaryKey  val id:Int?,
    @ColumnInfo(name = "usuario") val usuario: String,
    @ColumnInfo(name = "producto") val producto:String,
    @ColumnInfo(name = "cantidad") val cantidad:String,
    @ColumnInfo(name = "total") val compra:Int,
    @ColumnInfo(name = "fecha") val fecha:String
)
package com.wiedii.wiicandy.Helpers

import android.provider.BaseColumns

class CompraUtilidades {
    companion object {
        val nameDb = "wiicandi"
        val versionDb = 1

        val createTableUsuario =
            "CREATE TABLE ${Usuario.javaClass.name.toLowerCase()} (${Usuario.columnId} int primary key not null,${Usuario.columnNombre} varchar(30) );"
        val createTableCompra = "CREATE TABLE ${Compra.javaClass.name.toLowerCase()} (" +
                "${Compra.columnUsuario} varchar(11) not null," +
                "${Compra.columnProducto} varchar(20) not null," +
                "${Compra.columnCantidad} int(3) not null," +
                "${Compra.columnTotal} int(6) not null, " +
                "${Compra.columnFecha} varchar(30) not null);"

        val createTables= "$createTableUsuario $createTableCompra"
        val dropTables="DROP TABLE  IF EXISTS ${Usuario.javaClass.name.toLowerCase()} ; DROP TABLE IF EXISTS ${Compra.javaClass.name.toLowerCase()}}"


        class Usuario : BaseColumns {
            companion object {
                val columnId = "id"
                val columnNombre = "nombre"
            }
        }


        class Compra : BaseColumns {
            companion object {
                val nameTable = "compra"
                val columnUsuario = "usuario"
                val columnProducto = "producto"
                val columnCantidad = "cantidad"
                val columnTotal = "total"
                val columnFecha = "fecha"
            }

        }
    }
}
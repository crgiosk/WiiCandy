package com.wiedii.wiicandy.Helpers

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class CompraCrud(context: Context) {
    private var helper: DatabaseHelper? = null
    private lateinit var db: SQLiteDatabase

    init {
        helper = DatabaseHelper(context)
    }

    fun nuevaCompra(compra: Compra) {
        val contentValues = ContentValues()
        contentValues.put(CompraUtilidades.Companion.Compra.columnUsuario, compra.usuario)
        contentValues.put(CompraUtilidades.Companion.Compra.columnProducto, compra.producto)
        contentValues.put(CompraUtilidades.Companion.Compra.columnCantidad, compra.cantidad)
        contentValues.put(CompraUtilidades.Companion.Compra.columnTotal, compra.total)
        contentValues.put(CompraUtilidades.Companion.Compra.columnFecha, compra.fecha)

        saveNuevaCompra(contentValues)
    }

    private fun saveNuevaCompra(contentValues: ContentValues) {
        db = helper!!.writableDatabase
        db.insert(CompraUtilidades.Companion.Compra.nameTable, null, contentValues)
        db.close()
    }

    private fun setGetCompras() {
        val compras: ArrayList<Compra> = ArrayList()
        db = helper!!.readableDatabase

        val fields = arrayOf(
            CompraUtilidades.Companion.Compra.columnProducto,
            CompraUtilidades.Companion.Compra.columnCantidad,
            CompraUtilidades.Companion.Compra.columnTotal,
            CompraUtilidades.Companion.Compra.columnFecha
        )

        val cursor: Cursor = db.query(
            CompraUtilidades.Companion.Compra.nameTable,
            fields, null, null, null, null, CompraUtilidades.Companion.Compra.columnProducto
            // selection -> filtrar; args -> arreglosFilstros; group -> agrupar; having -> like; prderby -> ordenar
        // para filtrar se pondria "namefield = ?
        //en selection args -> arrayof(parameter)
        )

        while (cursor.moveToNext()){
            compras.add(
                Compra(
                    cursor.getString(cursor.getColumnIndex(CompraUtilidades.Companion.Compra.columnUsuario)),
                    cursor.getString(cursor.getColumnIndex(CompraUtilidades.Companion.Compra.columnProducto)),
                    cursor.getString(cursor.getColumnIndex(CompraUtilidades.Companion.Compra.columnCantidad)),
                    cursor.getString(cursor.getColumnIndex(CompraUtilidades.Companion.Compra.columnTotal)),
                    cursor.getString(cursor.getColumnIndex(CompraUtilidades.Companion.Compra.columnFecha))
                    )
            )
        }

        cursor.close()
        db.close()

    }
}
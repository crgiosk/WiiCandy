package com.wiedii.wiicandy.Helpers

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class CompraCrud(context: Context) {
    private val helper: DatabaseHelper = DatabaseHelper(context)
    private lateinit var db: SQLiteDatabase
    private lateinit var compras: ArrayList<Compra>

    fun getCompras(): ArrayList<Compra> = compras
    fun triggerGetCompras() {
        setGetCompras()
    }


    fun nuevaCompra(compra: Compra) {
        try {
            val contentValues = ContentValues()

            contentValues.put(CompraUtilidades.Companion.Compra.columnUsuario, compra.usuario)
            contentValues.put(CompraUtilidades.Companion.Compra.columnProducto, compra.producto)
            contentValues.put(CompraUtilidades.Companion.Compra.columnCantidad, compra.cantidad)
            contentValues.put(CompraUtilidades.Companion.Compra.columnTotal, compra.total)
            contentValues.put(CompraUtilidades.Companion.Compra.columnFecha, compra.fecha)
            Log.e("test", CompraUtilidades.createTables)
            saveNuevaCompra(contentValues)
        } catch (e: SQLiteException) {
            Log.e(this.javaClass.name, "Guardadda ${e.message}")
        }
    }

    private fun saveNuevaCompra(contentValues: ContentValues) {
        try {
            db = helper.writableDatabase
            Log.e("test", CompraUtilidades.Companion.Compra.nameTable)
            val test = db.insert("compra", null, contentValues)
            Log.e("insertCompra", "$test")
        } catch (e: SQLiteException) {
            Log.e("insertCompra", e.message!!.toUpperCase())
        }

        db.close()
    }

    private fun setGetCompras() {

        compras = ArrayList()
        db = helper.readableDatabase

        val fields = arrayOf(
            CompraUtilidades.Companion.Compra.columnUsuario,
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
        while (cursor.moveToNext()) {
            compras.add(
                Compra(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
                )

            )
        }
        cursor.close()
        db.close()
    }
}
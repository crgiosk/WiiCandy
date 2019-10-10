package com.wiedii.wiicandy.Helpers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, CompraUtilidades.nameDb, null, CompraUtilidades.versionDb) {
    val TAG = this.javaClass.simpleName
    override fun onCreate(db: SQLiteDatabase?) {
        Log.e("createtables compra", CompraUtilidades.createTables)

        db!!.execSQL(CompraUtilidades.createTables)
        Log.e(TAG, "Se creo la bd  ")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL(CompraUtilidades.dropTables)

        Log.e(TAG, "Se eliminaron las tablas de la  bd")
        onCreate(db)
    }
}
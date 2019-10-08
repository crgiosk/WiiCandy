package com.wiedii.wiicandy.DAO

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Compra::class), version = 1, exportSchema = false)
abstract class CompraRoomDatabase : RoomDatabase() {
    abstract fun compraDao(): CompraDao

    private class CompraDatabaseCallBack(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var compraDao = database.compraDao()
                    compraDao.deleteComprasUsuario()
                    var compra = Compra(1, "test1", "mani1", "31", 0, "ayer")
                    compraDao.insertCompra(compra)
                    compra = Compra(2, "test", "mani", "3", 0, "hoy")
                    compraDao.insertCompra(compra)

                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: CompraRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): CompraRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CompraRoomDatabase::class.java,
                    "wiicandy"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
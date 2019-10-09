package com.wiedii.wiicandy.DAO

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Compra::class), version = 1,exportSchema = false)
 abstract class CompraRoomDatabase : RoomDatabase() {
    abstract fun compraDao(): CompraDao

    companion object {
        @Volatile
        private var INSTANCE: CompraRoomDatabase? = null

        fun getDatabase(context: Context): CompraRoomDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    CompraRoomDatabase::class.java,
                    "wiicandy"
                ).build()
                INSTANCE= instance
                return instance
            }
        }
    }
}
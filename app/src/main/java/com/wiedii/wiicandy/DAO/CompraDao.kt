package com.wiedii.wiicandy.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CompraDao {

    @Query("SELECT * FROM Compra ")
    fun getAllCompras(): List<Compra>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCompra(compra: Compra)

    @Query("DELETE FROM Compra")
    suspend fun deleteComprasUsuario()
}
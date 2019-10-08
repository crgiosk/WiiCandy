package com.wiedii.wiicandy.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CompraDao {

    @Query("SELECT * FROM compra_tabla ")
    fun getAllCompras(): LiveData<List<Compra>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCompra(compra: Compra)

    @Query("DELETE FROM compra_tabla")
    suspend fun deleteComprasUsuario()

/*
    @Query("SELECT SUM(total) FROM compra_tabla")
    fun getDeuda(): LiveData<List<Compra>>
 */

}
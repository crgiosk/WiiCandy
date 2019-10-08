package com.wiedii.wiicandy.DAO

import androidx.lifecycle.LiveData

class CompraRepository(private val compraDao: CompraDao) {
    val allCompras: LiveData<List<Compra>> = compraDao.getAllCompras()
    suspend fun nuevaCompra(compra: Compra){
        compraDao.insertCompra(compra)
    }

}
package com.wiedii.wiicandy.UI.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.wiedii.wiicandy.DAO.Compra
import com.wiedii.wiicandy.DAO.CompraRepository
import com.wiedii.wiicandy.DAO.CompraRoomDatabase
import kotlinx.coroutines.launch

class ViewModelCompra(aplication: Application) : AndroidViewModel(aplication) {
    private var repositoryCompra: CompraRepository
    var allCompras: LiveData<List<Compra>>

    init {
        val compraDao= CompraRoomDatabase.getDatabase(aplication).compraDao()
        repositoryCompra= CompraRepository(compraDao)
        allCompras=repositoryCompra.allCompras
    }

    fun insert(compra: Compra)= viewModelScope.launch{
        repositoryCompra.nuevaCompra(compra)
    }


}
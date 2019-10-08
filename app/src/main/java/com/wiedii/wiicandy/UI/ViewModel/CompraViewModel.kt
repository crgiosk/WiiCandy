package com.wiedii.wiicandy.UI.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wiedii.wiicandy.DAO.Compra
import com.wiedii.wiicandy.DAO.CompraRepository
import com.wiedii.wiicandy.DAO.CompraRoomDatabase
import kotlinx.coroutines.launch

class CompraViewModel(application: Application): AndroidViewModel(application) {
    private var repository: CompraRepository

    val allCompras:LiveData<List<Compra>>

    init {
        val compraDao = CompraRoomDatabase.getDatabase(application,viewModelScope).compraDao()
        repository= CompraRepository(compraDao)
        allCompras= repository.allCompras
    }

    fun inserCompra(compra: Compra)=viewModelScope.launch{
        repository.nuevaCompra(compra)
        Log.e("insetCompra"," compra")
    }
}
package com.wiedii.wiicandy.UI.ViewModel

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wiedii.wiicandy.Helpers.Compra
import com.wiedii.wiicandy.Helpers.CompraCrud
import kotlinx.coroutines.launch

class ViewModelCompra(val aplication: Application) : AndroidViewModel(aplication) {

    private lateinit var crud: CompraCrud
    private var compras: MutableLiveData<ArrayList<Compra>> = MutableLiveData()


    fun getCompra(): LiveData<ArrayList<Compra>> = compras

    fun triggerGetCompra(){
        crud= CompraCrud(aplication)
        compras.value = crud.getCompras()
    }


}
package com.wiedii.wiicandy.UI.vistas


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.wiedii.wiicandy.DAO.Compra

import com.wiedii.wiicandy.R
import com.wiedii.wiicandy.UI.ViewModel.CompraViewModel
import com.wiedii.wiicandy.UI.ViewModel.CompraListAdapter
import kotlinx.android.synthetic.main.fragment_compra.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentCompra : Fragment() {
    private lateinit var compraViewModel: CompraViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compra, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        compraViewModel = ViewModelProviders.of(this).get(CompraViewModel::class.java)
        compraViewModel.allCompras.observe(this, Observer {
            it?.let {
                compraViewModel.allCompras
                Log.d(javaClass.name, "testeando ${it[0]}")
            }
        })
        buttonCompras.setOnClickListener {

            if (!validarTodosInputs()) {
                val compra = Compra(32,
                    "test",
                    editTextProducto.text.toString(),
                    editTextCantidad.text.toString(),
                    editTextTotal.text.toString().toInt(),
                    "ayer")
                compraViewModel.inserCompra(compra)
                Navigation.findNavController(it).navigate(R.id.action_fragmentHome_to_fragment_compras)
            } else {
                showError()
            }
        }

    }

    private fun validarTodosInputs(): Boolean {
        return editTextProducto.text.isNullOrEmpty() || editTextCantidad.text.isNullOrEmpty() || editTextTotal.text.isNullOrEmpty()
    }

    fun showError() {
        val required = "Informacion requerida"

        if (editTextProducto.text.isNullOrEmpty()) {
            editTextProducto.error = "No llevaste nada?"
        }
        if (editTextCantidad.text.isNullOrEmpty()) {
            editTextCantidad.error = "Llevaste algo que no se puede contar?"
        }
        if (editTextTotal.text.isNullOrEmpty()) {
            editTextTotal.error = "Te vas a ir sin pagar?"
        }

        if (editTextProducto.text.isNullOrEmpty() && editTextCantidad.text.isNullOrEmpty() && editTextTotal.text.isNullOrEmpty()) {
            editTextProducto.error = required
            editTextCantidad.error = required
            editTextTotal.error = required
            Snackbar.make(
                activity!!.findViewById(android.R.id.content),
                "Informacion requerida",
                Snackbar.LENGTH_LONG
            ).show()
        }

    }


}

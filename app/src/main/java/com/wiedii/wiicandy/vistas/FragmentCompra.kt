package com.wiedii.wiicandy.vistas


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar

import com.wiedii.wiicandy.R
import kotlinx.android.synthetic.main.fragment_compra.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentCompra : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compra, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonCompras.setOnClickListener {
           if (!validarTodosInputs()){
               Navigation.findNavController(it).navigate(R.id.action_fragmentHome_to_fragment_compras2)
           }else{
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

        if (editTextProducto.text.isNullOrEmpty() && editTextCantidad.text.isNullOrEmpty() && editTextTotal.text.isNullOrEmpty()){
            editTextProducto.error = required
            editTextCantidad.error = required
            editTextTotal.error = required
            Snackbar.make(activity!!.findViewById(android.R.id.content),"Informacion requerida",Snackbar.LENGTH_LONG).show()
        }

    }


}

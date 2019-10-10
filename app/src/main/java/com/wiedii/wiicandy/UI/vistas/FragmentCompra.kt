package com.wiedii.wiicandy.UI.vistas


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.wiedii.wiicandy.Helpers.Compra
import com.wiedii.wiicandy.Helpers.CompraCrud

import com.wiedii.wiicandy.R
import kotlinx.android.synthetic.main.fragment_compra.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt

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
        val compraCrud = CompraCrud(context!!)
        buttonCompras.setOnClickListener {
            if (!validarTodosInputs()) {
                if (editTextCantidad.text.length !in 1..2) {
                    editTextCantidad.error = "Tu dieta no te lo permite "
                } else {

                    compraCrud.nuevaCompra(compra())
                    Log.e("error", "Seteo de la clase compra")
                    Navigation.findNavController(it)
                        .navigate(R.id.action_fragmentHome_to_myCompraFragment)
                }
            } else {
                showError()
            }
        }

        buttonVerCompras.setOnClickListener {

            Navigation.findNavController(it)
                .navigate(R.id.action_fragmentHome_to_myCompraFragment)

        }
    }

    private fun compra(): Compra {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())

        val date = Date()
        Log.e("fecha",dateFormat.format(date))
        val icono = when (Random.nextInt(0..8)) {
            0 -> R.drawable.ic_001_candy_1
            1 -> R.drawable.ic_002_candy
            2 -> R.drawable.ic_003_toffee
            3 -> R.drawable.ic_004_candy_2
            4 -> R.drawable.ic_005_candy_bag
            5 -> R.drawable.ic_006_cookies
            6 -> R.drawable.ic_007_cookies_1
            7 -> R.drawable.ic_008_cookies_2
            8 -> R.drawable.ic_009_gingerbread_man
            else -> R.drawable.ic_launcher_foreground
        }
        return Compra(
            "12345",
            editTextProducto.text.toString(),
            editTextCantidad.text.toString(),
            editTextTotal.text.toString(),
            dateFormat.format(date),
            icono
        )

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

package com.wiedii.wiicandy.UI.vistas


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
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
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        //activity!!.window.setBackgroundDrawable(ColorDrawable(Color.BLACK))
        // getWindow().setBackgroundDrawable(new ColorDrawable(isLight ? Color.WHITE : Color.BLACK));
        editTextProducto.requestFocus()
        clearFields()
        val compraCrud = CompraCrud(context!!)
        buttonCompras.setOnClickListener {
            if (!validarTodosInputs()) {
                if (editTextCantidad.text.length !in 1..2) {
                    editTextCantidad.error = "Tu dieta no te lo permite "
                } else {

                    if  (compraCrud.nuevaCompra(compra())){
                        clearFields()
                        Log.e("error", "Seteo de la clase compra")
                        Navigation.findNavController(it)
                            .navigate(R.id.action_fragmentHome_to_myCompraFragment)
                    }else{
                        Toast.makeText(context,"Error al guardar la compra",Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                showError()
            }
        }

        buttonFotoProducto.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_fragmentHome_to_camara)
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


    private fun showError() {
        val required = "Informacion requerida"

        if (editTextProducto.text.isNullOrEmpty()) {
            textInputLayoutProducto.setError("No llevaste nada?")
        }else {
            textInputLayoutProducto.setError(null)
        }
        if (editTextCantidad.text.isNullOrEmpty()) {
            textInputLayoutCantidad.setError("No llevaste nada?")
        }else {
            textInputLayoutCantidad.setError(null)
        }
        if (editTextTotal.text.isNullOrEmpty()) {
            textInputLayoutTotal.setError("No llevaste nada?")
        }else {
            textInputLayoutTotal.setError(null)
        }

        if (editTextProducto.text.isNullOrEmpty() && editTextCantidad.text.isNullOrEmpty() && editTextTotal.text.isNullOrEmpty()) {
            textInputLayoutProducto.setError(required)
            editTextCantidad.setError(required)
            textInputLayoutTotal.setError(required)
            Snackbar.make(
                activity!!.findViewById(android.R.id.content),
                "Informacion requerida",
                Snackbar.LENGTH_LONG
            ).show()
        }else{
            textInputLayoutProducto.setError(null)
            textInputLayoutCantidad.setError(null)
            textInputLayoutTotal.setError(null)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        Toast.makeText(context,"se creo las opciones",Toast.LENGTH_LONG).show()
    }





    private fun clearFields() {
        editTextProducto.setText("")
        editTextCantidad.setText("")
        editTextTotal.setText("")
    }

    override fun onResume() {
        super.onResume()
        clearFields()
    }

}

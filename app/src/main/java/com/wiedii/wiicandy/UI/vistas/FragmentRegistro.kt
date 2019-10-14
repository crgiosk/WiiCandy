package com.wiedii.wiicandy.UI.vistas


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.Navigation

import com.wiedii.wiicandy.R
import kotlinx.android.synthetic.main.fragment_fragment_login.*
import kotlinx.android.synthetic.main.fragment_registro.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentRegistro : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSpinner()
    }

    private fun setSpinner(){
        val spinner_Sexo = arrayOf("Seleccione...","Hombre","Mujer")
         val adapter = ArrayAdapter<String>(activity!!.applicationContext,R.layout.spinner_item_style,spinner_Sexo)
        spinnerSexo.adapter = adapter
    }


}

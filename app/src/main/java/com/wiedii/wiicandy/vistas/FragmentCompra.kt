package com.wiedii.wiicandy.vistas


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

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
            Navigation.findNavController(it).navigate(R.id.action_fragmentHome_to_fragment_compras2)
        }
    }



}

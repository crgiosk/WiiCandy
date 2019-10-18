package com.wiedii.wiicandy.UI.vistas


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide

import com.wiedii.wiicandy.R
import kotlinx.android.synthetic.main.fragment_loading.*

/**
 * A simple [Fragment] subclass.
 */
class Loading : DialogFragment() {

    internal  var mensaje=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,R.style.FullScreemFragmentLoading)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loading, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        isCancelable=false
        textViewLoading.text = mensaje

        buttonLoadClose.setOnClickListener {
            this.dismiss()
            Navigation.findNavController(it).navigate(R.id.action_loading_to_fragmentHome)
        }
        Glide.with(this).load(R.drawable.logo_wiedii_icon2x).into(imageViewLoading)
    }



    companion object{
        const val tag="Cargando"
        fun newStance(mensaje:String): Loading{
            val dialog = Loading()
            dialog.mensaje = mensaje
            return dialog
        }

    }


}

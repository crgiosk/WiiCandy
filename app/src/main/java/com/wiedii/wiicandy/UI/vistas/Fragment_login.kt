package com.wiedii.wiicandy.UI.vistas


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.wiedii.wiicandy.R
import kotlinx.android.synthetic.main.fragment_fragment_login.*

/**
 * A simple [Fragment] subclass.
 */
class Fragment_login : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //editTextNumberPhone.hint="Su numero de celular aqui"
        buttonLogin_Entrar.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_fragment_login_to_fragmentHome)
        }
    }


}

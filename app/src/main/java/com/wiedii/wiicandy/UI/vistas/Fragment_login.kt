package com.wiedii.wiicandy.UI.vistas


import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.view.animation.Animation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.wiedii.wiicandy.MainActivity
import com.wiedii.wiicandy.R
import kotlinx.android.synthetic.main.fragment_fragment_login.*
import kotlinx.android.synthetic.main.fragment_loading.*

/**
 * A simple [Fragment] subclass.
 */
class Fragment_login : Fragment() {
    private lateinit var url: String
    private var load: Loading? = null

    private val main = MainActivity()



    private lateinit var bundle: Bundle
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED)

        //editTextNumberPhone.hint="Su numero de celular aqui"
        buttonLogin_Entrar.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_fragment_login_to_fragmentHome)
        }

        imageViewYouTube.setOnClickListener {
            navigateToRedesSociales("https://www.youtube.com/channel/UC9mkUgGiw31FV99-AMjk8jQ", it)
        }
        imageViewFacebook.setOnClickListener {
            navigateToRedesSociales("https://wiedii.squadlinx.com/login", it)
        }

        imageViewInstagram.setOnClickListener {
            navigateToRedesSociales("https://wiedii.squadlinx.com/login",it)
        }
        buttonLogin_Registrar.setOnClickListener {
            Toast.makeText(context,"test", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).navigate(R.id.action_fragment_login_to_fragmentRegistro)
        }
    }


    private fun navigateToRedesSociales( urlPage: String, it: View) {
        bundle = Bundle()
        bundle.putString("url", urlPage)
        Navigation.findNavController(it).navigate(R.id.action_fragment_login_to_redesSociales,bundle)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_black_light_mode_tes,menu)
    }



}

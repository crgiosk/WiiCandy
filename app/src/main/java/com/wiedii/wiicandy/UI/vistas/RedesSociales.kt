package com.wiedii.wiicandy.UI.vistas


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import android.widget.Toast
import com.wiedii.wiicandy.R
import kotlinx.android.synthetic.main.fragment_redes_sociales.*

/**
 * A simple [Fragment] subclass.
 */
class RedesSociales : Fragment() {
    private lateinit var bundle: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_redes_sociales, container, false)
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webViewRedesSociales.webChromeClient = object : WebChromeClient() {

        }

        webViewRedesSociales.webViewClient = object : WebViewClient() {

        }
        bundle = arguments!!.getString("url").toString()

        webViewRedesSociales.settings.javaScriptEnabled = true
        Log.e("urlRedes",bundle)
        Toast.makeText(context, bundle, Toast.LENGTH_LONG).show()
        webViewRedesSociales.loadUrl(bundle)
    }


}

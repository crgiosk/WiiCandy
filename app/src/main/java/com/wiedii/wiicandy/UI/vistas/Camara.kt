package com.wiedii.wiicandy.UI.vistas

import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import com.wiedii.wiicandy.Helpers.Fotos
import com.wiedii.wiicandy.R
import kotlinx.android.synthetic.main.activity_camara.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class Camara : AppCompatActivity() {
private var fotos:Fotos? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camara)

        fotos= Fotos(this,imageViewFotoProducto)
        buttonTomarFoto.setOnClickListener {
            //tomarFoto()
            fotos!!.tomarFoto()
        }

        buttonSeleccion.setOnClickListener {
            //seleccionarFoto()
            fotos!!.seleccionarFoto()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //requesPermissionResult(requestCode, permissions, grantResults)
        fotos!!.requesPermissionResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //activityResult(requestCode, resultCode, data)
        fotos!!.activityResult(requestCode, resultCode, data)
    }
}

package com.wiedii.wiicandy.UI.vistas

import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import com.wiedii.wiicandy.R
import kotlinx.android.synthetic.main.activity_camara.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.Manifest

class Camara : AppCompatActivity() {
    var solicitudTomarFoto=1

    val permisoCamara=android.Manifest.permission.CAMERA
    val permisoWriteStore = android.Manifest.permission.READ_EXTERNAL_STORAGE
    val permisoReadStore = android.Manifest.permission.WRITE_EXTERNAL_STORAGE

    private lateinit var urlFotoActual:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camara)
        buttonTomarFoto.setOnClickListener {
            pedir_permiso()
        }
    }


    @TargetApi(Build.VERSION_CODES.M)
    private fun solicitudPermision() {
        requestPermissions(arrayOf(permisoCamara,permisoWriteStore,permisoReadStore),solicitudTomarFoto)
    }

    fun pedir_permiso(){
        val contexto=ActivityCompat.shouldShowRequestPermissionRationale(this,permisoCamara)

        if(contexto){
            solicitudPermision()
        }else{
            solicitudPermision()
        }
    }

    fun disparaIntet(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (intent.resolveActivity(packageManager) != null){
            var archivoFoto:File? = null
            startActivityForResult(intent,solicitudTomarFoto)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            solicitudTomarFoto -> {
                if (grantResults.size > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[2] == PackageManager.PERMISSION_GRANTED
                ){
                    //Tenemos permiso
                    disparaIntet()
                }else{
                    //no permiso
                    Toast.makeText(this,"Concede permisos",Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //0 es cuando no eligio foto   // uno cuando si ps

        Log.e("resultImage",requestCode.toString())

        when (requestCode){
            solicitudTomarFoto ->{
                 if (resultCode == Activity.RESULT_OK){
                     //getImage
                     val extra= data!!.extras
                     val imageBitmap= extra!!.get("data") as Bitmap

                     imageViewFotoProducto.setImageBitmap(imageBitmap)
                 }else{
                     //Cancelo la captura
                     Toast.makeText(this,"cancelo foto ",Toast.LENGTH_LONG).show()
                 }
            }
        }
    }

    fun crearArchivoImgen(): File{
        val timeStamp = SimpleDateFormat("yyyMMdd_HHmmss").format(Date())
        val directorio= getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val nombreImagen= "JPEG_${timeStamp}_"
        val extencion=".jpg"

        val imagen= File.createTempFile(nombreImagen,extencion,directorio)
        urlFotoActual = imagen.absolutePath
        return imagen
    }





}

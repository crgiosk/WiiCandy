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
import com.wiedii.wiicandy.R
import kotlinx.android.synthetic.main.activity_camara.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class Camara : AppCompatActivity() {
    var solicitudTomarFoto = 1
    var solicitudSeleccionarFoto = 2
    val permisoCamara = android.Manifest.permission.CAMERA
    val permisoWriteStore = android.Manifest.permission.READ_EXTERNAL_STORAGE
    val permisoReadStore = android.Manifest.permission.WRITE_EXTERNAL_STORAGE

    private lateinit var urlFotoActual: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camara)
        buttonTomarFoto.setOnClickListener {
            pedir_permiso()
        }

        buttonSeleccion.setOnClickListener {
            seleccionarFoto()
        }
    }

    private fun seleccionarFoto() {
        pedir_permisoSeleccionarFoto()
    }

    private fun pedir_permisoSeleccionarFoto() {
        val contexto = ActivityCompat.shouldShowRequestPermissionRationale(this, permisoWriteStore)

        if (contexto) {
            solicitudPermisionSeleccionarFoto()
        } else {
            solicitudPermisionSeleccionarFoto()
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun solicitudPermisionSeleccionarFoto() {
        requestPermissions(
            arrayOf(permisoReadStore), solicitudSeleccionarFoto
        )
    }


    @TargetApi(Build.VERSION_CODES.M)
    private fun solicitudPermision() {
        requestPermissions(
            arrayOf(permisoCamara, permisoWriteStore, permisoReadStore),
            solicitudTomarFoto
        )
    }

    fun pedir_permiso() {
        val contexto = ActivityCompat.shouldShowRequestPermissionRationale(this, permisoCamara)

        if (contexto) {
            solicitudPermision()
        } else {
            solicitudPermision()
        }
    }

    fun disparaIntet() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (intent.resolveActivity(packageManager) != null) {
            var archivoFoto: File? = null

            archivoFoto = crearArchivoImgen()

            if (archivoFoto != null) {
                val urlf = FileProvider.getUriForFile(
                    this,
                    "com.wiedii.wiicandy.android.fileprovider",
                    archivoFoto
                )
                intent.putExtra(MediaStore.EXTRA_OUTPUT, urlf)
                startActivityForResult(intent, solicitudTomarFoto)
            }

        }
    }

    fun disparaIntetSeleccionarFoto() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(
            Intent.createChooser(intent, "selecionar una foto "),
            solicitudSeleccionarFoto
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            solicitudTomarFoto -> {
                if (grantResults.size > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[2] == PackageManager.PERMISSION_GRANTED
                ) {
                    //Tenemos permiso
                    disparaIntet()
                } else {
                    //no permiso
                    Toast.makeText(this, "Concede permisos", Toast.LENGTH_LONG).show()
                }
            }

            solicitudSeleccionarFoto -> {
                if (grantResults.size > 0) {
                    //Tenemos permiso
                    disparaIntetSeleccionarFoto()
                } else {
                    //no permiso
                    Toast.makeText(
                        this,
                        "Concede permisos para acceder a tus fotos",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //0 es cuando no eligio foto   // uno cuando si ps

        Log.e("resultImage", requestCode.toString())
        Log.e("result", Activity.RESULT_OK.toString())
        when (requestCode) {
            solicitudTomarFoto -> {
                if (resultCode == Activity.RESULT_OK) {
                    //getImage
                    /*
                    val extra= data!!.extras
                    val imageBitmap= extra!!.get("data") as Bitmap */
                    mostrarBitMap(urlFotoActual)
                    añadirImageGaleria()
                } else {
                    //Cancelo la captura
                    Toast.makeText(this, "cancelo foto ", Toast.LENGTH_LONG).show()
                }
            }

            solicitudSeleccionarFoto -> {
                Log.e("result", Activity.RESULT_OK.toString())
                if (resultCode == Activity.RESULT_OK) {
                    mostrarBitMap(data?.data.toString())

                }
            }
        }
    }

    private fun mostrarBitMap(url:String){
        val uri = Uri.parse(url)
        val stream = contentResolver.openInputStream(uri)
        val imageBitmap = BitmapFactory.decodeStream(stream)
        imageViewFotoProducto.setImageBitmap(imageBitmap)
    }


    fun crearArchivoImgen(): File {
        val timeStamp = SimpleDateFormat("yyyMMdd_HHmmss").format(Date())
        //val directorio = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        //para que guarde en la carpeta pictures, se reemplaza la carpeta directorio por dirPictures en imegen
        val directorio = Environment.getExternalStorageDirectory()
        val dirPictures = File("${directorio.absolutePath}/Pictures/")

        val nombreImagen = "JPEG_${timeStamp}_"
        val extencion = ".jpg"

        val imagen = File.createTempFile(nombreImagen, extencion, dirPictures)
        urlFotoActual = "file://Wii${imagen.absolutePath}"

        Log.d("urlImage", imagen.toString())
        return imagen
    }

    private fun añadirImageGaleria() {
        val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        val file = File(urlFotoActual)
        val uri = Uri.fromFile(file)
        intent.setData(uri)

    }


}

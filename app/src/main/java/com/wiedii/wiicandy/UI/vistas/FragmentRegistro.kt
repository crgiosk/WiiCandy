package com.wiedii.wiicandy.UI.vistas


import android.annotation.TargetApi
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.annotation.RequiresApi
import com.wiedii.wiicandy.R
import kotlinx.android.synthetic.main.fragment_registro.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentRegistro : Fragment() {
    private val spinner_Sexo_opciones = arrayOf("Seleccione...", "Hombre", "Mujer")
    private lateinit var nombres: EditText
    private lateinit var apellidos: EditText
    private lateinit var correo: EditText
    private lateinit var celular: EditText
    private lateinit var sexo: RadioGroup
    private lateinit var calendar: Calendar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setValues()

        editTextFechaNacimiento.setOnClickListener {

            Toast.makeText(context!!.applicationContext,"dayOfMonth month byear",Toast.LENGTH_LONG).show()
        }

        buttonRegistrar.setOnClickListener {
            alerts()

            if (!emptyFields()){
                Toast.makeText(context,"A guardar",Toast.LENGTH_LONG).show()
            }else{
                alerts()
            }
        }
    }


    private fun alerts() {
        val required = "Requerido"
        if (nombres.text.isNullOrEmpty()) {
            textInputLayoutNombres.setError(required)
        }else{
            textInputLayoutNombres.setError(null)
        }

        if (apellidos.text.isNullOrEmpty()) {
            textInputLayoutApellidos.setError(required)
        }else{
            textInputLayoutApellidos.setError(null)
        }

        if (editTextFechaNacimiento.text.isNullOrEmpty()) {
            TextInputLayoutFechaNacimiento.setError(required)
        }else{
            TextInputLayoutFechaNacimiento.setError(null)
        }

        if (!radioButtonHombre.isChecked && !radioButtonHombre.isChecked) {
            TextInputLayoutSexo.setError(required)
        }else{
            TextInputLayoutSexo.setError(null)
        }

        if (correo.text.isNullOrEmpty()) {
            TextInputLAyoutEmail.setError(required)
        }else if (!validarCorreo(correo.text.toString())){
            TextInputLAyoutEmail.setError("Correo no valido")
        }else{
            TextInputLAyoutEmail.setError(null)
        }

        if (celular.text.isNullOrEmpty()) {
            TextInputLayoutCelular.setError(required)
        }else{
            TextInputLayoutCelular.setError(null)
        }
    }

    private fun emptyFields() = nombres.text.isNullOrEmpty()
            || apellidos.text.isNullOrEmpty()
            || celular.text.isNullOrEmpty()
            || correo.text.isNullOrEmpty()
            || celular.text.isNullOrEmpty()
            && !radioButtonHombre.isChecked && !radioButtonHombre.isChecked

    private fun setValues() {
        nombres = editTextRegistroNombres
        apellidos = editTextRegistroApellidos
        sexo = radioGroupSexo
        celular = editTextRegistroCelular
        correo = editTextRegistroEmail
    }

    private fun validarCorreo(corre:String):Boolean{
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(corre).matches()
    }
}

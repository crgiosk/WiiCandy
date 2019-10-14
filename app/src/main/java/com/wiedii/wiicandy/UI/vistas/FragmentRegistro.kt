package com.wiedii.wiicandy.UI.vistas


import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.wiedii.wiicandy.R
import kotlinx.android.synthetic.main.fragment_registro.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentRegistro : Fragment() {
    private val spinner_Sexo_opciones = arrayOf("Seleccione...", "Hombre", "Mujer")
    private lateinit var nombres: EditText
    private lateinit var apellidos: EditText
    private lateinit var correo: EditText
    private lateinit var celular: EditText
    private lateinit var sexo: Spinner


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
        setValues()

        buttonRegistrar.setOnClickListener {
            alerts()

            if (!emptyFields()){
                Toast.makeText(context,"A guardar",Toast.LENGTH_LONG).show()
            }else{
                alerts()
            }
        }
    }

    private fun setSpinner() {
        val adapter = ArrayAdapter<String>(
            activity!!.applicationContext,
            R.layout.spinner_item_style,
            spinner_Sexo_opciones
        )
        spinnerSexo.adapter = adapter
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

        if (sexo.selectedItem.toString() == spinner_Sexo_opciones[0]) {
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
            || sexo.selectedItem.toString() == spinner_Sexo_opciones[0]

    private fun setValues() {
        nombres = editTextRegistroNombres
        apellidos = editTextRegistroApellidos
        sexo = spinnerSexo
        celular = editTextRegistroCelular
        correo = editTextRegistroEmail
    }

    fun validarCorreo(corre:String):Boolean{
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(corre).matches()
    }


}

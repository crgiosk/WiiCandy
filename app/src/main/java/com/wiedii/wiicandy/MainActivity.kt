package com.wiedii.wiicandy

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.fragment_fragment_login.*
import kotlinx.android.synthetic.main.fragment_registro.*

class MainActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_main)

        this.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

    fun ligh_dark_mode(state: Int) {
        var backColor = 0
        var ColorTextButton = 0
        var ColorTextInputs = 0
        var resource = 0
        when (state){
            R.id.DarkMode -> {
                backColor = Color.BLACK
                ColorTextButton = Color.BLACK
                ColorTextInputs = Color.WHITE
                resource = R.drawable.style_button_login_dark_mode
                constraintLayoutImagenRedes.setBackgroundResource(resource)
                //Toast.makeText(this, "Test Dark Mode", Toast.LENGTH_LONG).show()
            }

            R.id.LightMode -> {
                backColor = Color.WHITE
                ColorTextButton = Color.WHITE
                ColorTextInputs = Color.BLACK
                resource = R.drawable.style_button_login
                constraintLayoutImagenRedes.setBackgroundColor(ColorTextButton)
                Toast.makeText(this, "Test Light Mode", Toast.LENGTH_LONG).show()
            }
        }

        if (backColor != 0 && ColorTextButton != 0 && ColorTextInputs != 0 && resource != 0){
            fragmentlogin.setBackgroundDrawable(ColorDrawable(backColor))
            buttonLogin_Entrar.setBackgroundResource(resource)
            buttonLogin_Entrar.setTextColor(ColorTextButton)
            buttonLogin_Registrar.setBackgroundResource(resource)
            buttonLogin_Registrar.setTextColor(ColorTextButton)
            editTextNumberPhone.setTextColor(ColorTextInputs)
            editTextPassWord.setTextColor(ColorTextInputs)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_black_light_mode, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        ligh_dark_mode(item.itemId)
        return super.onOptionsItemSelected(item)
    }
}

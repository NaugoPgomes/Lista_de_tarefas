package com.naugo.listadetarefas.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.naugo.listadetarefas.MainActivity
import com.naugo.listadetarefas.R
import com.naugo.listadetarefas.service.repository.DataBaseHelper
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        cadastro_tela_login.setOnClickListener{
            val intent = Intent(this, RegisterActivity:: class.java)
            startActivity(intent)
        }


        button.setOnClickListener{
            logar()
        }
    }

    fun logar()
    {
        var helper = DataBaseHelper(applicationContext)

        var db = helper.readableDatabase

        var args = listOf<String>(email.text.toString(), senha.text.toString()).toTypedArray()

        var rs = db.rawQuery("SELECT * FROM GuestUsuario WHERE EMAIL = ? AND SENHA = ? ",args)

        if(rs.moveToNext())
        {
            val intent = Intent(this, MainActivity:: class.java)
            startActivity(intent)
            finish()
        }
        else
        {
            Toast.makeText(applicationContext, "Falha no Login", Toast.LENGTH_SHORT).show()
        }
    }

}
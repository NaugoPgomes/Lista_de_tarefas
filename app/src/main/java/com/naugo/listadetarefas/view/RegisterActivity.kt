package com.naugo.listadetarefas.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.naugo.listadetarefas.MainActivity
import com.naugo.listadetarefas.R
import com.naugo.listadetarefas.viewModel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: RegisterViewModel
    private var mGuestId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        setListeners()
        observe()

    }

    override fun onClick(v: View?) {
        val id = v?.id
        if (id == R.id.button_Registrar) {

            val email_usuario = email.text.toString()
            val senha_usuario = senha.text.toString()
            val confirmar_senha_usuario = confirmar_senha.text.toString()

            if(senha_usuario.equals(confirmar_senha_usuario))
            {
                mViewModel.Registrar(mGuestId,email_usuario, senha_usuario)
            }
            else
            {
                Toast.makeText(applicationContext, "A senha tem que estar igual nos dois campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setListeners()
    {
        button_Registrar.setOnClickListener(this)
    }

    private fun observe()
    {
        mViewModel.registrar.observe(this, Observer {
            if(it)
            {
                Toast.makeText(applicationContext, "Usuario Cadastrado com sucesso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity:: class.java)
                startActivity(intent)
                finish()
            }
            else
            {
                Toast.makeText(applicationContext, "Falha", Toast.LENGTH_SHORT).show()
                finish()
            }
        })
    }


}
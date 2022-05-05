package com.naugo.listadetarefas.view

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.naugo.listadetarefas.MainActivity
import com.naugo.listadetarefas.R
import com.naugo.listadetarefas.service.repository.DataBaseHelper
import com.naugo.listadetarefas.verifica.VerificaLeitorDeDigital
import kotlinx.android.synthetic.main.activity_login.*
import java.util.concurrent.Executor

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

        VerificaLeitorDeDigital.PodeLogarComDigital(this)

        mostrarAutenticacao()
    }

    private fun mostrarAutenticacao() {
        val executor: Executor = ContextCompat.getMainExecutor(this)

        val biometricPrompt = BiometricPrompt(
            this@LoginActivity,
            executor,
            object : BiometricPrompt.AuthenticationCallback()
            {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    finish()
                }
            }
        )

        val info: BiometricPrompt.PromptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Lista de tarefas")
            .setSubtitle("Desbloqueie seu celular")
            .setNegativeButtonText("Cancelar")
            .build()

        biometricPrompt.authenticate(info)
    }

    fun logar()
    {
        var helper = DataBaseHelper(applicationContext)

        var db = helper.readableDatabase

        var args = listOf<String>(email.text.toString(), senha.text.toString()).toTypedArray()

        var rs = db.rawQuery("SELECT * FROM Usuario WHERE EMAIL = ? AND SENHA = ? ",args)

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
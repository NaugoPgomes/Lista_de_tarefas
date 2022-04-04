package com.naugo.listadetarefas.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.naugo.listadetarefas.service.model.ModelUsuario
import com.naugo.listadetarefas.service.repository.Repositore

class RegisterViewModel(application: Application) : AndroidViewModel(application)
{

    private val mContext = application.applicationContext
    private var mRepositoryUsuario : Repositore = Repositore.getInstance(mContext)

    private var mRegistrarUsuario = MutableLiveData<Boolean>()
    val registrar: LiveData<Boolean> = mRegistrarUsuario



    fun Registrar(id: Int, email: String, senha: String)
    {
        val usuario = ModelUsuario(id,email, senha)
        mRegistrarUsuario.value = mRepositoryUsuario.register(usuario)
    }

}
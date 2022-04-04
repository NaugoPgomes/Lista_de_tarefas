package com.naugo.listadetarefas.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.naugo.listadetarefas.service.model.GuestModelUsuario
import com.naugo.listadetarefas.service.repository.Repositore

class RegisterViewModel(application: Application) : AndroidViewModel(application)
{

    private val mContext = application.applicationContext
    private var mRepositoryUsuario : Repositore = Repositore.getInstance(mContext)

    private var mRegistrarGuest = MutableLiveData<Boolean>()
    val registrarGuest: LiveData<Boolean> = mRegistrarGuest



    fun Registrar(id: Int, email: String, senha: String)
    {
        val guest = GuestModelUsuario(id,email, senha)
        mRegistrarGuest.value = mRepositoryUsuario.register(guest)
    }

}
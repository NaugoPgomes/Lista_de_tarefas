package com.naugo.listadetarefas.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.naugo.listadetarefas.service.model.GuestModel
import com.naugo.listadetarefas.service.repository.GuestRepositore

class CadastroTarefasViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = application.applicationContext
    private val mGuestRepositore: GuestRepositore = GuestRepositore.getInstance(mContext)

    private var mSalvarFormulario = MutableLiveData<Boolean>() // tem como mudar valor (por isso o m no inicio)
    val SalvarFormulario: LiveData<Boolean> = mSalvarFormulario // não da para mudar valor

    fun save(tarefa: String, data: String, hora: String, concluida: Boolean) {
        val guest = GuestModel(tarefa = tarefa, data = data, hora = hora, concluida = concluida)

        mSalvarFormulario.value = mGuestRepositore.save(guest)
    }

}
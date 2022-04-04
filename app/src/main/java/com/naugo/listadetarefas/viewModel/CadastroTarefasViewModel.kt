package com.naugo.listadetarefas.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.naugo.listadetarefas.service.model.GuestModel
import com.naugo.listadetarefas.service.repository.Repositore

class CadastroTarefasViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = application.applicationContext
    private val mRepositore: Repositore = Repositore.getInstance(mContext)

    private var mSalvarFormulario = MutableLiveData<Boolean>() // tem como mudar valor (por isso o m no inicio)
    val SalvarFormulario: LiveData<Boolean> = mSalvarFormulario // não da para mudar valor

    private var mGuest = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = mGuest

    fun save(id: Int, tarefa: String, data: String, hora: String, concluida: Boolean) {
        val guest = GuestModel(id, tarefa,data, hora, concluida)

        if(id == 0)
        {
            mSalvarFormulario.value = mRepositore.save(guest)
        }
        else
        {
            mSalvarFormulario.value = mRepositore.update(guest)
        }
    }

    fun load(id : Int)
    {
        mGuest.value = mRepositore.get(id)
    }

}
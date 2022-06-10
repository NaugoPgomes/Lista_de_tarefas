package com.naugo.listadetarefas.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.naugo.listadetarefas.service.constants.TarefasConstants
import com.naugo.listadetarefas.service.model.Model
import com.naugo.listadetarefas.service.repository.Repositore

class FinalizadasViewModel(application: Application) : AndroidViewModel(application) {

    private val mTarefasRepositore = Repositore.getInstance(application.applicationContext)

    private val mTarefasList = MutableLiveData<List<Model>>()
    val TarefastList: LiveData<List<Model>> = mTarefasList

    fun load(filter: Int) {
        if (filter == TarefasConstants.FILTER.EMPTY) {
            mTarefasList.value = mTarefasRepositore.getAll()
        } else {
            mTarefasList.value = mTarefasRepositore.getConcluidas()
        }
    }

    fun delete(id: Int) {
        mTarefasRepositore.deletar(id)
    }


}
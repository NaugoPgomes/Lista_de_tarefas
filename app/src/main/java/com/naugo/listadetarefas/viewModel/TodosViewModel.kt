package com.naugo.listadetarefas.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.naugo.listadetarefas.service.constants.GuestConstants
import com.naugo.listadetarefas.service.model.GuestModel
import com.naugo.listadetarefas.service.repository.GuestRepositore

class TodosViewModel(application: Application) : AndroidViewModel(application) {

    private val mGuestRepositore = GuestRepositore.getInstance(application.applicationContext)

    private val mGuestList = MutableLiveData<List<GuestModel>>()
    val guestList: LiveData<List<GuestModel>> = mGuestList

    fun load(filter: Int)
    {
        if(filter == GuestConstants.FILTER.EMPTY)
        {
            mGuestList.value = mGuestRepositore.getAll()
        }
       else
        {
            mGuestList.value = mGuestRepositore.getConcluidas()
        }
    }

    fun delete(id: Int)
    {
        mGuestRepositore.deletar(id)
    }
}
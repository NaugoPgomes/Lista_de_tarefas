package com.naugo.listadetarefas.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.naugo.listadetarefas.service.model.Model
import com.naugo.listadetarefas.service.repository.Repositore
import java.util.*
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class EditarTarefaViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = application.applicationContext
    private val mRepositore: Repositore = Repositore.getInstance(mContext)

    private var mSalvarFormulario =
        MutableLiveData<Boolean>() // tem como mudar valor (por isso o m no inicio)
    val SalvarFormulario: LiveData<Boolean> = mSalvarFormulario // não da para mudar valor

    private var mTarefas = MutableLiveData<Model>()
    val guest: LiveData<Model> = mTarefas

    fun save(id: Int, tarefa: String, data: String, hora: String, concluida: Boolean)
    {
        val guest = Model(id, tarefa, data, hora, concluida)

        if (id == 0) {
            mSalvarFormulario.value = mRepositore.save(guest)
        } else {
            mSalvarFormulario.value = mRepositore.update(guest)
        }
    }

    fun load(id: Int)
    {
        mTarefas.value = mRepositore.get(id)
    }
}
package com.naugo.listadetarefas.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.naugo.listadetarefas.service.model.Model
import com.naugo.listadetarefas.service.repository.Repositore
import com.naugo.listadetarefas.view.LoginActivity
import java.util.*
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class CadastroTarefasViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = application.applicationContext
    private val mRepositore: Repositore = Repositore.getInstance(mContext)

    private var mSalvarFormulario = MutableLiveData<Boolean>() // tem como mudar valor (por isso o m no inicio)
    val SalvarFormulario: LiveData<Boolean> = mSalvarFormulario // não da para mudar valor

    private var mTarefas = MutableLiveData<Model>()
    val guest: LiveData<Model> = mTarefas

    fun save(id: Int, tarefa: String, data: String, hora: String, concluida: Boolean) {
        val guest = Model(id, tarefa,data, hora, concluida)

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
        mTarefas.value = mRepositore.get(id)
    }

    fun enviarEmail(nomeTarefa: String)
    {

        var email = mRepositore.getEmail()

        var userName: String = "*************" // colocar o email
        var password: String = "*************" // palavra-passe de aplicações que vc consegue na area de segurança do google

        var messageToSend = nomeTarefa
        var properties: Properties = Properties()

        properties.put("mail.smtp.auth", "true")
        properties.put("mail.smtp.starttls.enable", "true")
        properties.put("mail.smtp.host", "smtp.gmail.com")
        properties.put("mail.smtp.port", "587")

        var session : Session = Session.getInstance(properties,
            object : javax.mail.Authenticator() {
                @Override
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(userName, password)
                }
            })
        try {
            val mimeMessage = MimeMessage(session)
            mimeMessage.setFrom(InternetAddress(userName))
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email))
            mimeMessage.setSubject("Uma nova tarefa foi adicionada na sua lista de tarefas")
            mimeMessage.setText(messageToSend)
            mimeMessage.sentDate = Date()

            val smtpTransport = session.getTransport("smtp")
            smtpTransport.connect()
            smtpTransport.sendMessage(mimeMessage, mimeMessage.allRecipients)
            smtpTransport.close()
        } catch (messagingException: MessagingException) {
            messagingException.printStackTrace()
        }
    }

}
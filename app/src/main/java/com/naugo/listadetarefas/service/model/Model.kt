package com.naugo.listadetarefas.service.model

data class GuestModel(val id: Int = 0, var tarefa: String, var data: String, var hora: String, var concluida: Boolean)
data class GuestModelUsuario(val id: Int = 0, var email: String, var senha: String )
// class de modelo, modelo da aplicação
package com.naugo.listadetarefas.service.model

data class Model(val id: Int = 0, var tarefa: String, var data: String, var hora: String, var concluida: Boolean)
data class ModelUsuario(val id: Int = 0, var email: String, var senha: String )
data class ModelEmailGet(var email: String)
// class de modelo, modelo da aplicação

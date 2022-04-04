package com.naugo.listadetarefas.service.constants

class DataBaseConstantsUsuario private constructor()
{
    object USUARIO{
        const val TABLE_NAME = "Usuario"

        object COLUMNS{
            const val ID = "id"
            const val EMAIL = "email"
            const val SENHA = "senha"
        }
    }

}
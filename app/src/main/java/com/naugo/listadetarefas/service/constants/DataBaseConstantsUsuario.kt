package com.naugo.listadetarefas.service.constants

class DataBaseConstantsUsuario private constructor()
{
    object GUEST_USUARIO{
        const val TABLE_NAME = "GuestUsuario"

        object COLUMNS{
            const val ID = "id"
            const val EMAIL = "email"
            const val SENHA = "senha"
        }
    }

}
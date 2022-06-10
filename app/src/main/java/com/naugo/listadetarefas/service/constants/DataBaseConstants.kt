package com.naugo.listadetarefas.service.constants

class DataBaseConstants private constructor()
{

    object TAREFAS
    {
        const val TABLE_NAME = "TAREFAS"

        object COLUMNS
        {
            const val ID = "id"
            const val TAREFA = "tarefa"
            const val DATA = "data"
            const val HORA = "hora"
            const val CONCLUIDA = "concluida"
        }

    }
}
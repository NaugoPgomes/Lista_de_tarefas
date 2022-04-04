package com.naugo.listadetarefas.service.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.naugo.listadetarefas.service.constants.DataBaseConstants
import com.naugo.listadetarefas.service.constants.DataBaseConstantsUsuario


class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,
        null, VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        // primeira vez que chamar o banco de dados, vai vim para o onCreate e ele vai criar o banco de dados
        db.execSQL(CREATE_TABLE_TAREFAS)
        db.execSQL(CREATE_TABLE_USUARIO)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // se já tiver um banco, chama o onUpgrade
    }

    companion object {
        private const val VERSION = 1
        private const val DATABASE_NAME = "lista_de_tarefas.db"

        private const val CREATE_TABLE_TAREFAS =
            ("create table " + DataBaseConstants.TAREFAS.TABLE_NAME + " ("
                    + DataBaseConstants.TAREFAS.COLUMNS.ID + " integer primary key autoincrement, "
                    + DataBaseConstants.TAREFAS.COLUMNS.TAREFA + " text, "
                    + DataBaseConstants.TAREFAS.COLUMNS.DATA + " text, "
                    + DataBaseConstants.TAREFAS.COLUMNS.HORA + " text, "
                    + DataBaseConstants.TAREFAS.COLUMNS.CONCLUIDA + " integer);")

        private const val CREATE_TABLE_USUARIO =
            ("create table " + DataBaseConstantsUsuario.USUARIO.TABLE_NAME + " ("
                    + DataBaseConstantsUsuario.USUARIO.COLUMNS.ID + " integer primary key autoincrement, "
                    + DataBaseConstantsUsuario.USUARIO.COLUMNS.EMAIL + " text, "
                    + DataBaseConstantsUsuario.USUARIO.COLUMNS.SENHA + " text);")
    }
}
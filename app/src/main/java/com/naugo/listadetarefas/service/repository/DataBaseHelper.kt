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
        db.execSQL(CREATE_TABLE_GUEST)
        db.execSQL(CREATE_TABLE_GUEST_USUARIO)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // se já tiver um banco, chama o onUpgrade
    }

    companion object {
        private const val VERSION = 1
        private const val DATABASE_NAME = "lista_de_tarefas.db"

        private const val CREATE_TABLE_GUEST =
            ("create table " + DataBaseConstants.GUEST.TABLE_NAME + " ("
                    + DataBaseConstants.GUEST.COLUMNS.ID + " integer primary key autoincrement, "
                    + DataBaseConstants.GUEST.COLUMNS.TAREFA + " text, "
                    + DataBaseConstants.GUEST.COLUMNS.DATA + " text, "
                    + DataBaseConstants.GUEST.COLUMNS.HORA + " text, "
                    + DataBaseConstants.GUEST.COLUMNS.CONCLUIDA + " integer);")

        private const val CREATE_TABLE_GUEST_USUARIO =
            ("create table " + DataBaseConstantsUsuario.GUEST_USUARIO.TABLE_NAME + " ("
                    + DataBaseConstantsUsuario.GUEST_USUARIO.COLUMNS.ID + " integer primary key autoincrement, "
                    + DataBaseConstantsUsuario.GUEST_USUARIO.COLUMNS.EMAIL + " text, "
                    + DataBaseConstantsUsuario.GUEST_USUARIO.COLUMNS.SENHA + " text);")
    }
}
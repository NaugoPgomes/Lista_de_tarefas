package com.naugo.listadetarefas.service.repository

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import com.naugo.listadetarefas.MainActivity
import com.naugo.listadetarefas.service.constants.DataBaseConstants
import com.naugo.listadetarefas.service.constants.DataBaseConstantsUsuario
import com.naugo.listadetarefas.service.model.Model
import com.naugo.listadetarefas.service.model.ModelUsuario

class Repositore private constructor(context: Context) {

    // inicio singleton
    private var mDataBaseHelper: DataBaseHelper = DataBaseHelper(context)

    companion object {
        private lateinit var repositore: Repositore // essa variavel guarda a instancia da classe

        fun getInstance(context: Context): Repositore {
            if (!::repositore.isInitialized) {
                repositore = Repositore(context)
            }

            return repositore
        }
    }
    // fim singleton

    @SuppressLint("Range")
    fun getAll(): List<Model> {
        val list: MutableList<Model> = ArrayList()

        return try {
            val db = mDataBaseHelper.readableDatabase

            val cursor = db.rawQuery("SELECT id, tarefa, data, hora, concluida FROM Tarefas WHERE concluida = 0",
                    null)

            if(cursor != null && cursor.count > 0)
            {
                while(cursor.moveToNext())
                {
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.TAREFAS.COLUMNS.ID))
                    val produto = cursor.getString(cursor.getColumnIndex(DataBaseConstants.TAREFAS.COLUMNS.TAREFA))
                    val data = cursor.getString(cursor.getColumnIndex(DataBaseConstants.TAREFAS.COLUMNS.DATA))
                    val hora = cursor.getString(cursor.getColumnIndex(DataBaseConstants.TAREFAS.COLUMNS.HORA))
                    val concluida = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.TAREFAS.COLUMNS.CONCLUIDA)) == 1)

                    val guest = Model(id, produto, data, hora,concluida)
                    list.add(guest)
                }

            }

            cursor?.close()

            list
        } catch (e: Exception) {
            list
        }

    }

    @SuppressLint("Range")
    fun getConcluidas(): List<Model>
    {
        val list: MutableList<Model> = ArrayList()

        return try {
            val db = mDataBaseHelper.readableDatabase

            val cursor = db.rawQuery("SELECT id, tarefa, data, hora, concluida FROM Tarefas WHERE concluida = 1",
                    null)

            if(cursor != null && cursor.count > 0)
            {
                while(cursor.moveToNext())
                {
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.TAREFAS.COLUMNS.ID))
                    val produto = cursor.getString(cursor.getColumnIndex(DataBaseConstants.TAREFAS.COLUMNS.TAREFA))
                    val data = cursor.getString(cursor.getColumnIndex(DataBaseConstants.TAREFAS.COLUMNS.DATA))
                    val hora = cursor.getString(cursor.getColumnIndex(DataBaseConstants.TAREFAS.COLUMNS.HORA))
                    val concluida = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.TAREFAS.COLUMNS.CONCLUIDA)) == 1)

                    val guest = Model(id, produto, data, hora, concluida)
                    list.add(guest)
                }

            }

            cursor?.close()

            list
        } catch (e: Exception) {
            list
        }
    }

    @SuppressLint("Range")
    fun get(id: Int): Model?
    {
        var guest: Model? = null
        return try {
            val db = mDataBaseHelper.readableDatabase

            val projection = arrayOf(DataBaseConstants.TAREFAS.COLUMNS.TAREFA,DataBaseConstants.TAREFAS.COLUMNS.DATA,
                    DataBaseConstants.TAREFAS.COLUMNS.HORA,DataBaseConstants.TAREFAS.COLUMNS.CONCLUIDA)

            val selection = DataBaseConstants.TAREFAS.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            val cursor = db.query(DataBaseConstants.TAREFAS.TABLE_NAME,projection, selection, args,
            null,null,null)

            if(cursor != null && cursor.count > 0)
            {
                cursor.moveToFirst()

                val produto = cursor.getString(cursor.getColumnIndex(DataBaseConstants.TAREFAS.COLUMNS.TAREFA))
                val data = cursor.getString(cursor.getColumnIndex(DataBaseConstants.TAREFAS.COLUMNS.DATA))
                val hora = cursor.getString(cursor.getColumnIndex(DataBaseConstants.TAREFAS.COLUMNS.HORA))
                val concluida = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.TAREFAS.COLUMNS.CONCLUIDA)) == 1)

                guest = Model(id, produto, data, hora, concluida)
            }

            cursor?.close()

            guest
        } catch (e: Exception) {
            guest
        }
    }


    fun save(tarefas: Model): Boolean {
        return try {
            val db = mDataBaseHelper.writableDatabase

            val values = ContentValues()
            values.put(DataBaseConstants.TAREFAS.COLUMNS.TAREFA, tarefas.tarefa)
            values.put(DataBaseConstants.TAREFAS.COLUMNS.DATA, tarefas.data)
            values.put(DataBaseConstants.TAREFAS.COLUMNS.HORA, tarefas.hora)
            values.put(DataBaseConstants.TAREFAS.COLUMNS.CONCLUIDA, tarefas.concluida)

            db.insert(DataBaseConstants.TAREFAS.TABLE_NAME, null, values)

            true
        } catch (e: Exception) {
            false
        }
    }

    fun update(tarefas: Model): Boolean {
        return try {
            val db = mDataBaseHelper.writableDatabase

            val values = ContentValues()
            values.put(DataBaseConstants.TAREFAS.COLUMNS.TAREFA, tarefas.tarefa)
            values.put(DataBaseConstants.TAREFAS.COLUMNS.DATA, tarefas.data)
            values.put(DataBaseConstants.TAREFAS.COLUMNS.HORA, tarefas.hora)
            values.put(DataBaseConstants.TAREFAS.COLUMNS.CONCLUIDA, tarefas.concluida)

            val selection = DataBaseConstants.TAREFAS.COLUMNS.ID + " = ?"
            val args = arrayOf(tarefas.id.toString())

            db.update(DataBaseConstants.TAREFAS.TABLE_NAME, values, selection, args)

            true
        } catch (e: Exception) {
            false
        }
    }

    fun deletar(id: Int): Boolean {
        return try {
            val db = mDataBaseHelper.writableDatabase


            val selection = DataBaseConstants.TAREFAS.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.TAREFAS.TABLE_NAME, selection, args)

            true
        } catch (e: Exception) {
            false
        }
    }

    fun register(usuarios: ModelUsuario): Boolean
    {
        return try {
            val db =  mDataBaseHelper.writableDatabase

            val values = ContentValues()
            values.put(DataBaseConstantsUsuario.USUARIO.COLUMNS.EMAIL, usuarios.email)
            values.put(DataBaseConstantsUsuario.USUARIO.COLUMNS.SENHA, usuarios.senha)

            db.insert(DataBaseConstantsUsuario.USUARIO.TABLE_NAME, null, values)

            true
        } catch (e: java.lang.Exception)
        {
            false
        }

    }

}
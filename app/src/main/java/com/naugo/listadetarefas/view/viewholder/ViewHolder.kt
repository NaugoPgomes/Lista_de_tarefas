package com.naugo.listadetarefas.view.viewholder

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.naugo.listadetarefas.R
import com.naugo.listadetarefas.service.model.Model
import com.naugo.listadetarefas.view.listener.Listener

class ViewHolder(itemView: View, private val listener: Listener) : RecyclerView.ViewHolder(itemView)
{
    // essa clase que armazena referencia aos elementos de interface

    fun bind(guest: Model)
    {
        val textTarefa = itemView.findViewById<TextView>(R.id.id_tarefa)
        textTarefa.text = guest.tarefa
        val textHora = itemView.findViewById<TextView>(R.id.id_hora)
        textHora.text = guest.hora
        val textData = itemView.findViewById<TextView>(R.id.id_data)
        textData.text = guest.data



        textTarefa.setOnClickListener {
            listener.onClick(guest.id)
        }

        textTarefa.setOnLongClickListener {

            AlertDialog.Builder(itemView.context)
                    .setTitle("Remoção da tarefa")
                    .setMessage("tem certeza que quer remover essa tarefa ? ")
                    .setPositiveButton("Sim"){ dialog, which ->
                        listener.onDelete(guest.id)
                    }
                    .setNeutralButton("Não", null)
                    .show()
            true
        }
    }



}
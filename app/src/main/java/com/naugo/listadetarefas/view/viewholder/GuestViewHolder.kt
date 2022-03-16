package com.naugo.listadetarefas.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.naugo.listadetarefas.R
import com.naugo.listadetarefas.service.model.GuestModel
import com.naugo.listadetarefas.view.listener.GuestListener

class GuestViewHolder(itemView: View, private val listener: GuestListener) : RecyclerView.ViewHolder(itemView)
{
    // essa clase que armazena referencia aos elementos de interface

    fun bind(guest: GuestModel)
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
    }
}
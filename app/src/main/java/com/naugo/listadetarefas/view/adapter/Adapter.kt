package com.naugo.listadetarefas.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naugo.listadetarefas.R
import com.naugo.listadetarefas.service.model.Model
import com.naugo.listadetarefas.view.listener.Listener
import com.naugo.listadetarefas.view.viewholder.ViewHolder

class Adapter : RecyclerView.Adapter<ViewHolder>() {

    private var mTarefasList: List<Model> = arrayListOf()
    private lateinit var mListener: Listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_guest, parent, false)
        return ViewHolder(item, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(mTarefasList[position])
    }

    override fun getItemCount(): Int {
        return mTarefasList.count()
    }

    fun updateTarefas(list: List<Model>)
    {
        mTarefasList = list
        notifyDataSetChanged()
    }

    fun attachListener(listener: Listener)
    {
        mListener = listener
    }

}

package com.naugo.listadetarefas.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.naugo.listadetarefas.viewModel.CadastroTarefasViewModel
import com.naugo.listadetarefas.R
import kotlinx.android.synthetic.main.activity_cadastro_tarefa.*

class CadastroTarefaActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: CadastroTarefasViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_tarefa)

        mViewModel = ViewModelProvider(this).get(CadastroTarefasViewModel::class.java)

        setListeners()
    }


    private fun setListeners()
    {
        button_save.setOnClickListener(this)
    }

    override fun onClick(v: View?)
    {
        val id = v?.id
        if(id == R.id.button_save)
        {

        }
    }
}
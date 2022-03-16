package com.naugo.listadetarefas.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
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
        observe()
    }

    override fun onClick(v: View?) {
        val id = v?.id
        if (id == R.id.button_save) {
            val nome_tarefa = tarefa.text.toString()
            val data_tarefa = data.text.toString()
            val hora_tarefa = hora.text.toString()
            val concluida_tarefa = radio_Finalizada.isChecked


            mViewModel.save(nome_tarefa, data_tarefa, hora_tarefa, concluida_tarefa)
        }
    }

    private fun observe()
    {
        mViewModel.SalvarFormulario.observe(this, Observer
        {
            if(it)
            {
                Toast.makeText(applicationContext, "Sucesso", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(applicationContext, "Falha", Toast.LENGTH_SHORT).show()
            }
            finish()
        })
    }

    private fun setListeners() {
        button_save.setOnClickListener(this)
    }


}
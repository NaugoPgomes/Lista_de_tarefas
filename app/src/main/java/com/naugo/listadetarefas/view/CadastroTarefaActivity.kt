package com.naugo.listadetarefas.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.naugo.listadetarefas.R
import com.naugo.listadetarefas.service.constants.TarefasConstants
import com.naugo.listadetarefas.viewModel.CadastroTarefasViewModel
import kotlinx.android.synthetic.main.activity_cadastro_tarefa.*
import java.lang.RuntimeException
import java.text.SimpleDateFormat
import java.util.*
import javax.mail.*
import javax.mail.internet.*


class CadastroTarefaActivity : AppCompatActivity(), View.OnClickListener, DatePickerDialog.OnDateSetListener{

    private lateinit var mViewModel: CadastroTarefasViewModel
    private var mGuestId: Int = 0
    private val mDateFormat = SimpleDateFormat("dd/MM/yyyy")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_tarefa)

        mViewModel = ViewModelProvider(this).get(CadastroTarefasViewModel::class.java)

        setListeners()
        observe()
        loadData()

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
    }

    private fun loadData()
    {
        val bundle = intent.extras
        if(bundle != null)
        {
            mGuestId = bundle.getInt(TarefasConstants.TAREFASID)
            mViewModel.load(mGuestId)
        }
    }

    override fun onClick(v: View?) {
        val id = v?.id
        if (id == R.id.button_save) {
            val nome_tarefa = tarefa.text.toString()
            val data_tarefa = data.text.toString()
            val hora_tarefa = hora.text.toString()
            val concluida_tarefa = CheckBox_Finalizada.isChecked


            mViewModel.save(mGuestId, nome_tarefa, data_tarefa, hora_tarefa, concluida_tarefa)

            mViewModel.enviarEmail(nome_tarefa)

        }
        else if (id == R.id.data) {
            showDatePicker()
        }
    }

    private fun showDatePicker() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this, this, year, month, day).show()
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        data.text = mDateFormat.format(calendar.time)
    }

    private fun observe() {
        mViewModel.SalvarFormulario.observe(this, Observer
        {
            if (it) {
            } else {
                Toast.makeText(applicationContext, "Falha", Toast.LENGTH_SHORT).show()
            }
            finish()
        })
        mViewModel.guest.observe(this, Observer {
            tarefa.setText(it.tarefa)
            hora.setText(it.hora)
            data.setText(it.data)
            CheckBox_Finalizada.isChecked = it.concluida
        })

    }

    private fun setListeners() {
        button_save.setOnClickListener(this)
        data.setOnClickListener(this)
    }


}
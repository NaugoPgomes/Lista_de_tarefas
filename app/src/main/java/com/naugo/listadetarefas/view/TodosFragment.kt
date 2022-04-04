package com.naugo.listadetarefas.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.naugo.listadetarefas.R
import com.naugo.listadetarefas.service.constants.TarefasConstants
import com.naugo.listadetarefas.view.adapter.GuestAdapter
import com.naugo.listadetarefas.view.listener.GuestListener
import com.naugo.listadetarefas.viewModel.TodosViewModel

class TodosFragment : Fragment() {

  private lateinit var todosViewModel: TodosViewModel
  private val mAdapter: GuestAdapter = GuestAdapter()
  private lateinit var mListener: GuestListener

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    todosViewModel =
            ViewModelProvider(this).get(TodosViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_todos, container, false)

    // RecyclerView
    // 1 - obter a recycler
    val recylcer = root.findViewById<RecyclerView>(R.id.todos_guests)

    //2 - definir um layout
    recylcer.layoutManager = LinearLayoutManager(context)

    //3 - definir um adapter

    recylcer.adapter = mAdapter

    mListener = object : GuestListener{
      override fun onClick(id: Int) {
        val intent = Intent(context, CadastroTarefaActivity::class.java)

        val bundle = Bundle()
        bundle.putInt(TarefasConstants.GUESTID,id)

        intent.putExtras(bundle)

        startActivity(intent)
      }

      override fun onDelete(id: Int)
      {
        todosViewModel.delete(id)
        todosViewModel.load(TarefasConstants.FILTER.EMPTY)
      }

    }

    mAdapter.attachListener(mListener)

    observer()



    return root
  }

  override fun onResume()
  {
    super.onResume()
    todosViewModel.load(TarefasConstants.FILTER.EMPTY)

  }

  private fun observer() {
    todosViewModel.guestList.observe(viewLifecycleOwner, Observer {
      mAdapter.updateGuests(it)
    })
  }
}
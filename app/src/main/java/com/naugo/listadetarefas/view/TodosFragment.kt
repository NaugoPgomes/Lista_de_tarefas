package com.naugo.listadetarefas.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.naugo.listadetarefas.R
import com.naugo.listadetarefas.viewModel.TodosViewModel

class TodosFragment : Fragment() {

  private lateinit var todosViewModel: TodosViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    todosViewModel =
            ViewModelProvider(this).get(TodosViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_home, container, false)
    val textView: TextView = root.findViewById(R.id.text_home)
    todosViewModel.text.observe(viewLifecycleOwner, Observer {
      textView.text = it
    })
    return root
  }
}
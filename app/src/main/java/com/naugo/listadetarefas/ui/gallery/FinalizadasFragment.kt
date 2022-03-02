package com.naugo.listadetarefas.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.naugo.listadetarefas.R

class FinalizadasFragment : Fragment() {

  private lateinit var finalizadasViewModel: FinalizadasViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    finalizadasViewModel =
            ViewModelProvider(this).get(FinalizadasViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_gallery, container, false)
    val textView: TextView = root.findViewById(R.id.text_gallery)
    finalizadasViewModel.text.observe(viewLifecycleOwner, Observer {
      textView.text = it
    })
    return root
  }
}
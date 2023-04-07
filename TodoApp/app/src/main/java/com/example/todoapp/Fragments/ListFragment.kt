package com.example.todoapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.Adapters.RecyclerviewAdapter
import com.example.todoapp.R
import com.example.todoapp.ViewModel.Todo_VIEW_MODEL
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment(){

    lateinit var recyclerView: RecyclerView
    lateinit var floatingActionButton: FloatingActionButton
    lateinit var adapter_rv: RecyclerviewAdapter
    lateinit var viewModel: Todo_VIEW_MODEL

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_list, container, false)

        //initialization of views
        recyclerView=view.findViewById(R.id.recyclerview_listfragment)
        floatingActionButton=view.findViewById(R.id.floatingActionButton)
        adapter_rv= RecyclerviewAdapter(this.requireContext())
        viewModel=ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(Todo_VIEW_MODEL::class.java)
        setuprecyclerview()
        viewModel.data.observe(viewLifecycleOwner, Observer {
            adapter_rv.setdata(it)
        })


        floatingactionbutton_click()


        return view
    }

    private fun setuprecyclerview() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager=LinearLayoutManager(this.requireContext())
        recyclerView.adapter=adapter_rv
    }
    private fun floatingactionbutton_click(){
        floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_add2)
        }
    }

}
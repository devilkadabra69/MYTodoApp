package com.example.todoapp.Fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.RoomDB.Todo_Note
import com.example.todoapp.ViewModel.Todo_VIEW_MODEL


class AddFragment : Fragment() {

    lateinit var title:EditText
    lateinit var note:EditText
    lateinit var addbtn:TextView
     var viewModel: Todo_VIEW_MODEL?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_add, container, false)
        //initialization of views
        viewModel=ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(Todo_VIEW_MODEL::class.java)
        title=view.findViewById(R.id.title_addFragment)
        note=view.findViewById(R.id.note_addFragment)
        addbtn=view.findViewById(R.id.Add_btn)


        addbtn_click()
        return view
    }
    private fun addbtn_click(){
        addbtn.setOnClickListener {
            if(checkdata(title.text.toString(),note.text.toString())){
                viewModel?.add_data_to_db(Todo_Note(0,title.text.toString(),note.text.toString()))
                findNavController().navigate(R.id.action_add2_to_listFragment)
                Toast.makeText(context,"Successfully Added !!!",Toast.LENGTH_SHORT).show()
            }else
            {
                Toast.makeText(context,"Not Added !!! Missing Data",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun checkdata(title:String,note:String):Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(note))
    }
}
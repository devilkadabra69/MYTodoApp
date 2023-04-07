package com.example.todoapp.Fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoapp.R
import com.example.todoapp.RoomDB.Todo_Note
import com.example.todoapp.ViewModel.Todo_VIEW_MODEL

class EditUpdateFragment : Fragment() {

    private val args by navArgs<EditUpdateFragmentArgs>()
    lateinit var title:EditText
    lateinit var note:EditText
    lateinit var updatebutton:TextView
    lateinit var viewModel: Todo_VIEW_MODEL
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_edit_update, container, false)
        //initialization
        title=view.findViewById(R.id.title_updateFragment)
        note=view.findViewById(R.id.note_updateFragment)
        updatebutton=view.findViewById(R.id.Update_btn)
        viewModel=ViewModelProvider.AndroidViewModelFactory(requireActivity().application).create(Todo_VIEW_MODEL::class.java)


        title.text=args.currentItem.title.toString().toEditable()
        note.text=args.currentItem.note.toString().toEditable()



        updatebutton.setOnClickListener {
            if(checkdata(title.text.toString(),note.text.toString())){
                viewModel.update_data_to_db(Todo_Note(id=args.currentItem.id.toInt(), title = title.text.toString(), note = note.text.toString()))
                Toast.makeText(context?.applicationContext,"Update Done!",Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_editUpdateFragment_to_listFragment)
            }else{
                Toast.makeText(context?.applicationContext,"Fill The Blanks",Toast.LENGTH_SHORT).show()
            }

        }


        return view
    }
    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

    private fun checkdata(title:String,note:String):Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(note))
    }

}
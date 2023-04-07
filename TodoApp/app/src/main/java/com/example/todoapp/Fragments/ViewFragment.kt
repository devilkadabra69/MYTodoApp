package com.example.todoapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.todoapp.R

class ViewFragment : Fragment() {
    private val args by navArgs<ViewFragmentArgs>()
    private lateinit var title_view:TextView
    private lateinit var note_view:TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_view, container, false)
        title_view=view.findViewById(R.id.title_viewfragment)
        note_view=view.findViewById(R.id.note_viewfragment)

        title_view.text=args.currentTodo.title.toString()
        note_view.text=args.currentTodo.note.toString()
        return view
    }

}
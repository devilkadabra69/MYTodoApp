package com.example.todoapp.Adapters

import android.app.Application
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import com.example.todoapp.*
import com.example.todoapp.Fragments.ListFragmentDirections
import com.example.todoapp.RoomDB.Todo_Note
import com.example.todoapp.ViewModel.Todo_VIEW_MODEL
import kotlinx.coroutines.delay


class RecyclerviewAdapter(var context:Context):RecyclerView.Adapter<RecyclerviewAdapter.holder>() {
    var data= emptyList<Todo_Note>()

    inner class holder(itemview: View):RecyclerView.ViewHolder(itemview){
        var title=itemview.findViewById<TextView>(R.id.title_rcv_view)
        var note=itemview.findViewById<TextView>(R.id.note_rcv_view)


    }
    fun setdata(note: List<Todo_Note>){
        this.data=note
        RecycledViewPool().clear()
        notifyDataSetChanged();
        notifyItemInserted(data.size-1)
        if(this.data.isEmpty()){
            Toast.makeText(context.applicationContext,"Empty List",Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {
       var view=LayoutInflater.from(context).inflate(R.layout.rcv_view,parent,false)
        return holder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: holder, position: Int) {
        val currentuser=data.get(position)
       holder.title.text=data[position].title.toString()
       holder.note.text=data[position].note.toString()
        holder.itemView.setOnClickListener{
            val action=ListFragmentDirections.actionListFragmentToViewnoteFragment(currentuser)
            holder.itemView.findNavController().navigate(action)
        }
        var view=holder.itemView.setOnLongClickListener {
            val popup=PopupMenu(context,it)
            popup.inflate(R.menu.popup_long_press)
            popup.setOnMenuItemClickListener {
                when(it.itemId)
                {
                    R.id.delete_popup-> {
                        var alertDialog=AlertDialog.Builder(context)
                        alertDialog.setTitle("Delete ?")
                        alertDialog.setPositiveButton("No", DialogInterface.OnClickListener { _, _ ->
                            alertDialog.setCancelable(true)
                        })
                        alertDialog.setNegativeButton("Yes", DialogInterface.OnClickListener { _, _ ->
                            val viewModel=ViewModelProvider.AndroidViewModelFactory.getInstance(Application()).create(Todo_VIEW_MODEL::class.java)
                            viewModel.delete_data_to_db(Todo_Note(currentuser.id.toInt(),currentuser.title.toString(),currentuser.note.toString()))
                        })
                        alertDialog.show()
                        true
                    }
                    R.id.edit_popup->{
                        val action= ListFragmentDirections.actionListFragmentToEditUpdateFragment(currentuser)
                        holder.itemView.findNavController().navigate(action)
                        true}
                }
                true
            }
            popup.setForceShowIcon(true)
            popup.show()
            return@setOnLongClickListener true
        }
    }

}

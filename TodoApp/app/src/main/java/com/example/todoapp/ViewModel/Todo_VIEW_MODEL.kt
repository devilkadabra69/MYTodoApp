package com.example.todoapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.Repository.Todo_REPO
import com.example.todoapp.RoomDB.DataBaseTODO
import com.example.todoapp.RoomDB.Todo_DAO
import com.example.todoapp.RoomDB.Todo_Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Todo_VIEW_MODEL(application: Application):AndroidViewModel(application) {

    var data:LiveData<List<Todo_Note>>
    var repo: Todo_REPO
    init {
        val dao=DataBaseTODO.getInstance(application.applicationContext).getDao()
        repo=Todo_REPO(dao)
        data=repo.readalldata
    }


    fun add_data_to_db(todoNote: Todo_Note){
        viewModelScope.launch(Dispatchers.IO) {
            repo.add_data(todoNote)
        }

    }

    fun update_data_to_db(todoNote: Todo_Note){
        viewModelScope.launch(Dispatchers.IO) {
            repo.replace_data(todoNote)
        }

    }

    fun delete_data_to_db(todoNote: Todo_Note){
        viewModelScope.launch(Dispatchers.IO) {
            repo.remove_data(todoNote)
        }

    }
}
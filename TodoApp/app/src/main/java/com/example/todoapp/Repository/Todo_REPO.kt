package com.example.todoapp.Repository

import androidx.lifecycle.LiveData
import com.example.todoapp.RoomDB.Todo_DAO
import com.example.todoapp.RoomDB.Todo_Note
import kotlinx.coroutines.coroutineScope

class Todo_REPO(val dao: Todo_DAO){
    public val readalldata:LiveData<List<Todo_Note>> =dao.fetch()


    suspend fun add_data(note: Todo_Note){
        dao.insert(note)
    }

    suspend fun remove_data(note: Todo_Note){
        dao.delete(note)
    }

    suspend fun replace_data(note: Todo_Note){
        dao.update(note)
    }

}
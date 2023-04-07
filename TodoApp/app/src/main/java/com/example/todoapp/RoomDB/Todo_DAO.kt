package com.example.todoapp.RoomDB

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface Todo_DAO {

    @Insert
    suspend fun insert(todoNote: Todo_Note)

    @Delete
    suspend fun delete(todoNote: Todo_Note)

    @Update
    suspend fun update(todoNote: Todo_Note)

    @Query("SELECT * FROM My_Todo_Table")
    fun fetch():LiveData<List<Todo_Note>>
}
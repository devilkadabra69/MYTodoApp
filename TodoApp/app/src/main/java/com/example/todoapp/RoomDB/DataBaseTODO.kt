package com.example.todoapp.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Todo_Note::class],version = 1,exportSchema = true)
abstract class DataBaseTODO: RoomDatabase() {

    abstract fun getDao():Todo_DAO
    companion object {
        private var instance: DataBaseTODO? = null

        @Synchronized
        fun getInstance(ctx: Context): DataBaseTODO {
            if(instance == null)
                instance = Room.databaseBuilder(ctx.applicationContext, DataBaseTODO::class.java,
                    "note_database")
                    .build()

            return instance!!

        }
}
}
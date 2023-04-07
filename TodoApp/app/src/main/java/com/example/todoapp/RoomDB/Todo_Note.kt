package com.example.todoapp.RoomDB

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@kotlinx.android.parcel.Parcelize
@Entity(tableName = "My_Todo_Table")
data class Todo_Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("Id")
    var id:Int,
    @ColumnInfo("Title")
    var title:String,
    @ColumnInfo("Note")
    var note:String) : Parcelable
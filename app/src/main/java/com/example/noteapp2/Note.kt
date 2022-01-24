package com.example.noteapp2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.StringReader

@Entity(tableName = "note_table")
class Note (
    @ColumnInfo(name = "title") val title : String,
    @ColumnInfo(name = "description") val description : String,
    @ColumnInfo(name = "timeStamp") val timeStamp : String
        ){
    @PrimaryKey(autoGenerate = true) var id = 0
}
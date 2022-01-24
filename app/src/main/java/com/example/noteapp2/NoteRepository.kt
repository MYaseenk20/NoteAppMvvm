package com.example.noteapp2

import androidx.lifecycle.LiveData

class NoteRepository(val noteDao: NoteDao) {
    val allNote : LiveData<List<Note>> = noteDao.getAllNote()

    suspend fun insert(note: Note){
        noteDao.insert(note)
    }
    suspend fun delete(note: Note){
        noteDao.delete(note)
    }
    suspend fun update(note: Note){
        noteDao.update(note)
    }
}
package com.example.noteapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnClickedNote, OnClickedNoteDelete {
    lateinit var mAdaptor : NoteAdaptor
    lateinit var viewModel : NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this)
        mAdaptor = NoteAdaptor(this,this)
        recyclerView.adapter = mAdaptor
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNote.observe(this,{list->
            list?.let {
                mAdaptor.updateNote(it)
            }

        })
        idFAB.setOnClickListener { 
            val intent = Intent(this@MainActivity,Edit_update::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun updateNote(note: Note) {
        val intent = Intent(this@MainActivity,Edit_update::class.java)
        intent.putExtra("noteType","edit")
        intent.putExtra("title",note.title)
        intent.putExtra("description",note.description)
        intent.putExtra("Id",note.id)
        startActivity(intent)
        this.finish()
    }

    override fun deleteNote(note: Note) {
        viewModel.delete(note)
    }
}
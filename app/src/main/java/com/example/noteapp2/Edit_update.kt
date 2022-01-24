package com.example.noteapp2


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_edit_update.*
import java.text.SimpleDateFormat
import java.util.*

class Edit_update : AppCompatActivity() {
    lateinit var mainViewModel: NoteViewModel
    var id = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_update)
        mainViewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        val getType = intent.getStringExtra("noteType")
        if (getType.equals("edit")){
            supportActionBar?.title = "Update Note"
            val Ntitle = intent.getStringExtra("title")
            val Ndescription = intent.getStringExtra("description")
            id = intent.getIntExtra("Id",-1)
            idBtn.setText("Update Note")
            idEdtNoteName.setText(Ntitle)
            idEdtNoteDesc.setText(Ndescription)
        }else{
            supportActionBar?.title = "Add New Note"
            idBtn.setText("Save Note")
        }
        idBtn.setOnClickListener {
            val title = idEdtNoteName.text.toString()
            val description = idEdtNoteDesc.text.toString()
            if (getType.equals("edit")) {
                val sdf = SimpleDateFormat("dd MM yyyy - HH:mm")
                val currentDate : String = sdf.format(Date())
                val updateNote = Note(title,description,currentDate)
                updateNote.id = id
                mainViewModel.update(updateNote)
                Toast.makeText(this,"Note Update",Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, MainActivity::class.java))
                this.finish()
            }else{
                val sdf = SimpleDateFormat("dd MM yyyy - HH:mm")
                val currentDate : String = sdf.format(Date())
                mainViewModel.insert(Note(title,description,currentDate))
                Toast.makeText(this,"Note Added",Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, MainActivity::class.java))
                this.finish()
            }
        }

    }
}
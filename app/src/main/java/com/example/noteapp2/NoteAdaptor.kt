package com.example.noteapp2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_view.view.*

class NoteAdaptor(val listenerDelete:OnClickedNoteDelete,val listenerUpdate:OnClickedNote):RecyclerView.Adapter<NoteViewHolder>() {
    val allNote =  ArrayList<Note>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_view,parent,false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentPosition = allNote[position]
        holder.title.text = currentPosition.title
        holder.time.text = currentPosition.timeStamp
        holder.delete.setOnClickListener {
            listenerDelete.deleteNote(currentPosition)
        }
        holder.itemView.setOnClickListener {
            listenerUpdate.updateNote(currentPosition)
        }
    }

    override fun getItemCount(): Int {
        return allNote.size
    }
    fun updateNote(newlist: List<Note>){
        allNote.clear()
        allNote.addAll(newlist)
        notifyDataSetChanged()
    }
}
class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val title = itemView.idTVNote
    val time = itemView.idTVDate
    val delete = itemView.idIVDelete
}
interface OnClickedNote{
    fun updateNote(note:Note)
}
interface OnClickedNoteDelete{
    fun deleteNote(note:Note)
}
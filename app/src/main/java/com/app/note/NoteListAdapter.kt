package com.app.note

import android.view.ViewGroup
import android.view.LayoutInflater
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class NoteListAdapter internal constructor(
    context: Context,
    val listener: CustomItemClickListener
) : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {


    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mNotes = emptyList<Note>()

    var context = context
    private var currentPostion = 0


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        currentPostion = position

        var current  = mNotes[position]
        holder.noteItemView.text = current?.title
        holder.noteItemView1.text = current?.description
        Log.i("text completed", "completed " + position)
        //Toast.makeText(context,"Title: "+holder.noteItemView.text.toString(),Toast.LENGTH_SHORT).show()
    /*    holder.setOnCustomItemClickListener(object : CustomItemClickListener {
            override fun onCustomItemClickListener(view: View, position: Int) {
                Log.i("bind view is worked", "noteed e" + position)
                Toast.makeText(context, "Title: " + holder.noteItemView.text.toString(), Toast.LENGTH_SHORT).show()
            }
        })*/
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        Log.i("item view", "item view")
        val itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false)
        return NoteViewHolder(itemView,listener)
    }

    internal fun setWords(notes: List<Note>) {
        this.mNotes = notes
        notifyDataSetChanged()
    }

    override fun getItemCount() = mNotes.size


    inner class NoteViewHolder(itemView: View, val listener: CustomItemClickListener) :
        RecyclerView.ViewHolder(itemView){

        val noteItemView: TextView = itemView.findViewById(R.id.notetitle) as TextView
        val noteItemView1: TextView = itemView.findViewById(R.id.notedescription) as TextView

        init {
            itemView.setOnClickListener {
                    listener.onCustomItemClickListener(currentPostion)
            }
        }
    }

}


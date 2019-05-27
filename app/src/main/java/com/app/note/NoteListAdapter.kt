package com.app.note

import android.view.ViewGroup
import android.view.LayoutInflater
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class NoteListAdapter internal constructor(context: Context)
    : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {


    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mNotes = emptyList<Note>()

    var context=context

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {

        val noteItemView: TextView = itemView.findViewById(R.id.notetitle) as TextView
        val notItemView1: TextView = itemView.findViewById(R.id.notedescription) as TextView

        init {
            itemView.setOnClickListener { this }
        }

        var customItemClickListener:CustomItemClickListener?=null

        fun setOnCustomItemClickListener(itemClickListener: CustomItemClickListener){
            this.customItemClickListener=itemClickListener
        }

        override fun onClick(v: View?) {
            this.customItemClickListener!!.onCustomItemClickListener(v!!, adapterPosition)
        }
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val current = mNotes[position]
        holder.noteItemView.text=current.title
        holder.notItemView1.text=current.description
        Log.i("text completed","inserted")
        holder.setOnCustomItemClickListener(object :CustomItemClickListener{
            override fun onCustomItemClickListener(view: View, position: Int) {
                Log.i("bind view is worked","noteed e")
                Toast.makeText(context,"Title: "+holder.noteItemView.text.toString(),Toast.LENGTH_SHORT).show()
            }
        })
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        Log.i("item view" ,"item view")
        val itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false)
        return NoteViewHolder(itemView)
    }

    internal fun setWords(notes: List<Note>) {
        this.mNotes = notes
        notifyDataSetChanged()
    }

    override fun getItemCount()= mNotes.size
}


package com.example.note_app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.note_app.databinding.ItemDesignBinding

class NoteAdapter:ListAdapter<Note,NoteViewHilder>(comperator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHilder {
       return NoteViewHilder(ItemDesignBinding.inflate
           (LayoutInflater.from(parent.context),parent
           ,false))
    }

    override fun onBindViewHolder(holder: NoteViewHilder, position: Int) {

        getItem(position).let {

            holder.binding.apply {
                noteTittle.text=it.title
                DateTv.text=it.date
                TimeTv.text=it.time
            }
        }

    }
    companion object{
        var comperator= object :DiffUtil.ItemCallback<Note>(){
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
               return oldItem==newItem
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return  oldItem==newItem
            }

        }
    }
}
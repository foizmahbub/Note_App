package com.example.note_app

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities =[Note::class], version = 1 )
abstract class NoteDatabase :RoomDatabase(){
    abstract fun getNoteDao():NoteDao
}
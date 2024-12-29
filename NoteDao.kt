package com.example.note_app

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
@Dao
interface NoteDao {
    @Insert
    fun insertData(note: Note)
    @Update
    fun updateData(note: Note)
    @Delete
    fun deleteData(note: Note)
    @Query("select * From Note")
    fun getAllData():List<Note>
}
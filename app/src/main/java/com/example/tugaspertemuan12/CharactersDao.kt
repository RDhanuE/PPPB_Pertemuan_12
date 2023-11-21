package com.example.tugaspertemuan12

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CharactersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(characters: Characters)

    @Update
    fun update(characters: Characters)

    @Delete
    fun delete(characters: Characters)

    @get:Query("SELECT * from Characters ORDER BY id ASC")
    val allCharacters : LiveData<List<Characters>>
}

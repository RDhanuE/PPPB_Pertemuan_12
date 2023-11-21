package com.example.tugaspertemuan12

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "Characters")
data class Characters(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id : Int = 0,

    @ColumnInfo(name = "name")
    val char_name : String,

    @ColumnInfo(name = "char_class")
    val char_class : String,

    @ColumnInfo(name = "hp")
    val hp : Int,

    @ColumnInfo(name = "mana")
    val mana : Int,

    @ColumnInfo(name = "char_weapon")
    val char_weapon : String
)

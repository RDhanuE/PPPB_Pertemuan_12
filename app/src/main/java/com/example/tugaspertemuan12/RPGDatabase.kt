package com.example.tugaspertemuan12

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room.databaseBuilder

@Database(entities = [Characters::class], version = 1, exportSchema = false)
abstract class RPGDatabase : RoomDatabase(){
    abstract fun charactersDao() : CharactersDao?

    companion object{
        @Volatile
        private var INSTANCE : RPGDatabase? = null
        fun getDatabase ( context: Context) : RPGDatabase? {
            if (INSTANCE == null){
                synchronized(RPGDatabase::class.java) {
                    INSTANCE = databaseBuilder(context.applicationContext, RPGDatabase::class.java, "RPG_database").build()
                }
            }
        return INSTANCE
        }
    }
}
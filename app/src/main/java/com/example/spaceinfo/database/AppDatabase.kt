package com.example.spaceinfo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.spaceinfo.models.ThemeItem

@Database(entities = [ThemeItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun nasaCardDao() : NasaCardDao
    companion object{
        const val databaseName = "NasaApp"
    }
}
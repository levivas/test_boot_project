package com.levivas.interviewproject.persistance.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.levivas.interviewproject.models.local.Main

@Database(
    entities = [Main::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mainDao(): MainDao
}
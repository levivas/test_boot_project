package com.levivas.interviewproject.models.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "main_table")
class Main(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val timestamp: Long
)
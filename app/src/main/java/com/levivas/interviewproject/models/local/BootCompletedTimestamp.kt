package com.levivas.interviewproject.models.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "boot_competed_timestamp_table")
class BootCompletedTimestamp(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val timestamp: Long
)
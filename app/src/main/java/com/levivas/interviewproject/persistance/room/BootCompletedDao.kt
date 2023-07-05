package com.levivas.interviewproject.persistance.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.levivas.interviewproject.models.local.BootCompletedTimestamp
import kotlinx.coroutines.flow.Flow

@Dao
interface BootCompletedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBootCompletedTimestamp(bootCompletedTimestamp: BootCompletedTimestamp)

    @Query("SELECT * FROM boot_competed_timestamp_table")
    fun getBootCompletedTimestamps(): Flow<List<BootCompletedTimestamp>>
}
package com.levivas.interviewproject.persistance.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.levivas.interviewproject.models.local.Main
import kotlinx.coroutines.flow.Flow

@Dao
interface MainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMain(main: Main)

    @Query("SELECT * FROM main_table")
    fun getItems(): Flow<List<Main>>

//    @Query("SELECT COUNT(*) FROM main_table")
//    fun getBootCounts(): Flow<Int>
}
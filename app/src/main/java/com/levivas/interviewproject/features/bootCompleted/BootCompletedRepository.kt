package com.levivas.interviewproject.features.bootCompleted

import com.levivas.interviewproject.models.local.BootCompletedTimestamp
import com.levivas.interviewproject.persistance.room.BootCompletedDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BootCompletedRepository @Inject constructor(private val bootCompletedDao: BootCompletedDao) {

    suspend fun insertBootCompletedTimestamp(bootCompletedItem: BootCompletedTimestamp) = bootCompletedDao.insertBootCompletedTimestamp(bootCompletedItem)

    fun getBootCompletedTimestamps() = bootCompletedDao.getBootCompletedTimestamps()
}
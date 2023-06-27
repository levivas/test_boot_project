package com.levivas.interviewproject.features.main

import com.levivas.interviewproject.models.local.Main
import com.levivas.interviewproject.persistance.room.MainDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(private val mainDao: MainDao) {

    suspend fun insertMain(main: Main) = mainDao.insertMain(main)

    fun getItems() = mainDao.getItems()
}
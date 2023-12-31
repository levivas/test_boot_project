package com.levivas.interviewproject.injection

import android.content.Context
import androidx.room.Room
import com.levivas.interviewproject.persistance.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DbModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "boot_completed_db").build()
    }

    @Singleton
    @Provides
    fun provideBootCompletedDao(appDatabase: AppDatabase) = appDatabase.bootCompletedDao()
}

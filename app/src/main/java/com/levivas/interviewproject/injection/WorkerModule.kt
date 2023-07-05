package com.levivas.interviewproject.injection

import android.content.Context
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.levivas.interviewproject.workers.BootCompletedWorker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object WorkerModule {

    @Singleton
    @Provides
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager =
        WorkManager.getInstance(context)

    @Singleton
    @Provides
    fun provideWorkRequestBuilder(): PeriodicWorkRequest =
        PeriodicWorkRequestBuilder<BootCompletedWorker>(15, TimeUnit.MINUTES).build()
}
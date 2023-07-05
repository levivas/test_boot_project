package com.levivas.interviewproject.injection

import android.content.Context
import com.levivas.interviewproject.extensions.NotificationHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NotificationModule {

    @Singleton
    @Provides
    fun provideNotificationHelper(@ApplicationContext context: Context): NotificationHelper {
        return NotificationHelper(context)
    }
}
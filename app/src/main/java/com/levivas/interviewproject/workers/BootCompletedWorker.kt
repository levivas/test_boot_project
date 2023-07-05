package com.levivas.interviewproject.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.levivas.interviewproject.extensions.NotificationHelper
import com.levivas.interviewproject.features.bootCompleted.BootCompletedRepository
import com.levivas.interviewproject.models.local.BootCompletedTimestamp
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.ZoneId

@HiltWorker
class BootCompletedWorker @AssistedInject constructor(
    private val repository: BootCompletedRepository,
    private val notificationHelper: NotificationHelper,
    @Assisted val appContext: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val timeInLong = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        repository.insertBootCompletedTimestamp(BootCompletedTimestamp(timestamp = timeInLong))
        var notificationDescription: String
        repository.getBootCompletedTimestamps().collectLatest {
            notificationDescription = when(it.size) {
                0 -> "No boots detected"
                1 -> "The boot was detected with timestamp = $timeInLong"
                else -> "Last boot time delta ${it.last().timestamp - it.dropLast(1).last().timestamp}"
            }
            notificationHelper.createNotification(notificationDescription)
        }
//        null ?: return@withContext Result.retry()
        return@withContext Result.success()
    }
}
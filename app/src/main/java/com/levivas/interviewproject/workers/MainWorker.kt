package com.levivas.interviewproject.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.levivas.interviewproject.extensions.NotificationHelper
import com.levivas.interviewproject.features.main.MainRepository
import com.levivas.interviewproject.models.local.Main
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.ZoneId

@HiltWorker
class MainWorker @AssistedInject constructor(
    private val repository: MainRepository,
    @Assisted val appContext: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val timeInLong = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        repository.insertMain(Main(timestamp = timeInLong))
        var notificationTitle = ""
        repository.getItems().collectLatest {
            notificationTitle = when(it.size) {
                0 -> "No boots detected"
                1 -> "The boot was detected with timestamp = $timeInLong"
                else -> "Last boot time delta ${it.last().timestamp - it.dropLast(1).last().timestamp}"
            }
        }
        NotificationHelper(appContext).createNotification(
            notificationTitle,
            "")
//        null ?: return@withContext Result.retry()
        return@withContext Result.success()
    }
}
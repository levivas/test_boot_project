package com.levivas.interviewproject.workers

import android.content.Context
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

private const val MAIN_WORKER_NAME_ONE_TIME = "mainWorkerOneTime"
private const val MAIN_WORKER_NAME_PERIODIC = "mainWorkerPeriodic"

fun Context.runOneTimeWorkManager() {
    WorkManager.getInstance(this)
        .enqueueUniqueWork(
            MAIN_WORKER_NAME_ONE_TIME,
            ExistingWorkPolicy.REPLACE,
            provideOneTimeWorkerRequest()
        )
}

fun Context.runPeriodicTimeWorkManager() {
    WorkManager.getInstance(this)
        .enqueueUniquePeriodicWork(
            MAIN_WORKER_NAME_PERIODIC,
            ExistingPeriodicWorkPolicy.REPLACE,
            providePeriodicTimeWorkerRequest()
        )
}

private fun provideOneTimeWorkerRequest() = OneTimeWorkRequestBuilder<MainWorker>()
    .setConstraints(provideWorkerConstraints())
    .build()

private fun providePeriodicTimeWorkerRequest() = PeriodicWorkRequestBuilder<MainWorker>(15, TimeUnit.MINUTES)
    .setConstraints(provideWorkerConstraints())
    .build()

private fun provideWorkerConstraints() = Constraints.Builder()
    .setRequiredNetworkType(NetworkType.CONNECTED)
    .build()

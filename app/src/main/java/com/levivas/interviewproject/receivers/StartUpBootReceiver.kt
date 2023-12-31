package com.levivas.interviewproject.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

const val BOOT_COMPLETED_WORKER_NAME_PERIODIC = "bootCompletedWorkerPeriodic"

@AndroidEntryPoint
class StartUpBootReceiver : BroadcastReceiver() {
    @Inject
    lateinit var workManager: WorkManager

    @Inject
    lateinit var periodicWorkRequest: PeriodicWorkRequest

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            workManager.enqueueUniquePeriodicWork(
                BOOT_COMPLETED_WORKER_NAME_PERIODIC,
                ExistingPeriodicWorkPolicy.UPDATE,
                periodicWorkRequest
            )
        }
    }
}
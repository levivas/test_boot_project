package com.levivas.interviewproject.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.levivas.interviewproject.workers.runPeriodicTimeWorkManager

class StartUpBootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.runPeriodicTimeWorkManager()
    }
}
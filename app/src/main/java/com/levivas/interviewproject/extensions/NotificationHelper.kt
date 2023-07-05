package com.levivas.interviewproject.extensions

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.levivas.interviewproject.R
import com.levivas.interviewproject.application.MainActivity

const val CHANNEL_ID  = "reminder_channel_id"
const val CHANNEL_NAME  = "Counter notification"
const val CHANNEL_DESCRIPTION  = "Every 15 min triggers changing counter"
const val NOTIFICATION_ID = 1

class NotificationHelper (private val context: Context) {
    fun createNotification(description: String){
        createNotificationChannel()
        val intent = Intent(context, MainActivity:: class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_MUTABLE)
        } else {
            PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
            )
        }
        val icon = BitmapFactory.decodeResource(context.resources, R.drawable.ic_launcher_background)
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setLargeIcon(icon)
            .setContentTitle("Boot completed counter")
            .setContentText(description)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, notification)

    }

    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = CHANNEL_DESCRIPTION
            }
            val notificationManager =  context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
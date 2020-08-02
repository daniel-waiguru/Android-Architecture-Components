package tech.danielwaiguru.androidarchitecturecomponents.utils

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import tech.danielwaiguru.androidarchitecturecomponents.R
import tech.danielwaiguru.androidarchitecturecomponents.ui.main.MainActivity

private const val NOTIFICATION_ID = 0
private const val NOTIFICATION_CHANNEL_ID = "1"
private const val NOTIFICATION_CHANNEL_NAME = ""
fun NotificationManager.sendNotification(messageBody: String, context: Context){
    val notificationsIntent = Intent(context, MainActivity::class.java)
    val pendingIntent = PendingIntent
        .getActivity(context, 0, notificationsIntent, 0)
    val notification = NotificationCompat.Builder(context,
        NOTIFICATION_CHANNEL_ID
    )
        .setSmallIcon(R.drawable.ic_refresh)
        .setContentTitle("Synchronization service")
        .setContentText(messageBody)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

    notify(NOTIFICATION_ID, notification.build())
}
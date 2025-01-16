package com.example.productivityapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Create a notification channel
        val notificationChannel = NotificationChannel("REMINDER_CHANNEL", "Reminder Channel", NotificationManager.IMPORTANCE_HIGH)
        notificationChannel.description = "Reminder notifications"

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
    }
}
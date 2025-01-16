package com.example.productivityapp



import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.Toast

class TaskWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // Perform this loop procedure for each App Widget that belongs to this provider
        for (appWidgetId in appWidgetIds) {
            // Get the task details from SharedPreferences
            val preferences = context.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
            val taskTitle = preferences.getString("TASK_TITLE", "No Title")
            val taskDescription = preferences.getString("TASK_DESCRIPTION", "No Description")
            val taskPriority = preferences.getString("TASK_PRIORITY", "No Priority")

            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.activity_layout_widget)
            views.setTextViewText(R.id.widget_title, taskTitle)
            views.setTextViewText(R.id.widget_description, taskDescription)
            views.setTextViewText(R.id.widget_priority, "Priority: $taskPriority")

            // Create an Intent to launch AddTaskActivity when the widget is clicked
            val intent = Intent(context, Addtask_Activity::class.java)
            val pendingIntent = PendingIntent.getActivity(
                context, 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )

            views.setOnClickPendingIntent(R.id.widget_title, pendingIntent)

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        // When the widget is deleted, you can handle any cleanup here
        Toast.makeText(context, "Widget removed", Toast.LENGTH_SHORT).show()
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

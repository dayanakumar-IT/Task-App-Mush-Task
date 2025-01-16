package com.example.productivityapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.*

class Addtask_Activity : AppCompatActivity() {
    private lateinit var preferences: SharedPreferences

    private lateinit var sundayText: TextView
    private lateinit var mondayText: TextView
    private lateinit var tuesdayText: TextView
    private lateinit var wednesdayText: TextView
    private lateinit var thursdayText: TextView
    private lateinit var fridayText: TextView
    private lateinit var saturdayText: TextView

    private lateinit var newTaskTitle: EditText
    private lateinit var newTaskDescription: EditText
    private lateinit var prioritySpinner: Spinner

    // New views for displaying task data
    private lateinit var taskCard: View
    private lateinit var displayTaskTitle: TextView
    private lateinit var displayTaskDescription: TextView
    private lateinit var displayTaskPriority: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addtask)


        // Timer image setup
        val timerImage: ImageView = findViewById(R.id.timer)
        timerImage.setOnClickListener {
            val intent = Intent(this, Timer_Activity::class.java)
            startActivity(intent)
        }


        preferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val namereceive: TextView = findViewById(R.id.namereceive)
        val name = preferences.getString("NAME", "")
        namereceive.text = String.format("Hello, %s", name)

        sundayText = findViewById(R.id.sunday_text)
        mondayText = findViewById(R.id.monday_text)
        tuesdayText = findViewById(R.id.tuesday_text)
        wednesdayText = findViewById(R.id.wednesday_text)
        thursdayText = findViewById(R.id.thursday_text)
        fridayText = findViewById(R.id.friday_text)
        saturdayText = findViewById(R.id.saturday_text)

        newTaskTitle = findViewById(R.id.newtasktitle)
        newTaskDescription = findViewById(R.id.description)
        prioritySpinner = findViewById(R.id.priority_level_spinner)

        val createButton: Button = findViewById(R.id.create_button)
        createButton.setOnClickListener {
            saveTask()
        }

        val displayButton: Button = findViewById(R.id.display_button)
        displayButton.setOnClickListener {
            displayTask()
        }

        // Set up delete button
        val deleteButton: Button = findViewById(R.id.delete_button)
        deleteButton.setOnClickListener {
            deleteTask()
        }

        updateDateViews()

        // Initialize task card views
        taskCard = findViewById(R.id.task_card)
        displayTaskTitle = findViewById(R.id.display_task_title)
        displayTaskDescription = findViewById(R.id.display_task_description)
        displayTaskPriority = findViewById(R.id.display_task_priority)
    }

    private fun updateDateViews() {
        val calendar = Calendar.getInstance()
        calendar.firstDayOfWeek = Calendar.SUNDAY
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)

        val dateFormat = SimpleDateFormat("dd", Locale.getDefault())
        val currentDate = dateFormat.format(calendar.time)

        val days = arrayOf(
            sundayText,
            mondayText,
            tuesdayText,
            wednesdayText,
            thursdayText,
            fridayText,
            saturdayText
        )

        for (day in days) {
            val date = dateFormat.format(calendar.time)
            day.text = String.format("%s\n%s", getDayOfWeek(calendar), date)
            day.setTextColor(if (date == currentDate) Color.WHITE else Color.BLACK)
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }
    }

    private fun getDayOfWeek(calendar: Calendar): String {
        return when (calendar.get(Calendar.DAY_OF_WEEK)) {
            Calendar.SUNDAY -> "Su"
            Calendar.MONDAY -> "Mo"
            Calendar.TUESDAY -> "Tu"
            Calendar.WEDNESDAY -> "We"
            Calendar.THURSDAY -> "Th"
            Calendar.FRIDAY -> "Fr"
            Calendar.SATURDAY -> "Sa"
            else -> ""
        }
    }

    private fun saveTask() {
        val title = newTaskTitle.text.toString().trim()
        val description = newTaskDescription.text.toString().trim()
        val priority = prioritySpinner.selectedItem.toString()

        // Check if any field is empty and display a toast message
        if (title.isEmpty() || description.isEmpty() || priority.isEmpty()) {
            Toast.makeText(this, "Please fill all fields before saving the task", Toast.LENGTH_SHORT).show()
            return
        }

        // Save task details to SharedPreferences
        val editor = preferences.edit()
        editor.putString("TASK_TITLE", title)
        editor.putString("TASK_DESCRIPTION", description)
        editor.putString("TASK_PRIORITY", priority)
        editor.apply()

        Toast.makeText(this, "Task successfully created", Toast.LENGTH_SHORT).show()

        // Optionally, clear the fields after saving
        newTaskTitle.text.clear()
        newTaskDescription.text.clear()
        prioritySpinner.setSelection(0) // Resetting spinner to default
    }

    private fun displayTask() {
        val title = preferences.getString("TASK_TITLE", "No title")
        val description = preferences.getString("TASK_DESCRIPTION", "No description")
        val priority = preferences.getString("TASK_PRIORITY", "No priority")

        // Create SpannableString to apply color only to the word "Title:"
        val titleText = SpannableString("Title: $title")
        titleText.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.gradient_end)), 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Create SpannableString to apply color only to the word "Description:"
        val descriptionText = SpannableString("Description: $description")
        descriptionText.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.gradient_end)), 0, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Create SpannableString to apply color only to the word "Priority:"
        val priorityText = SpannableString("Priority: $priority")
        priorityText.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.gradient_start)), 0, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Set the text to TextViews
        displayTaskTitle.text = titleText
        displayTaskDescription.text = descriptionText
        displayTaskPriority.text = priorityText

        // Make the task card visible
        taskCard.visibility = View.VISIBLE
    }

    private fun deleteTask() {
        // Remove task details from SharedPreferences
        val editor = preferences.edit()
        editor.remove("TASK_TITLE")
        editor.remove("TASK_DESCRIPTION")
        editor.remove("TASK_PRIORITY")
        editor.apply()

        // Clear the displayed task data
        displayTaskTitle.text = ""
        displayTaskDescription.text = ""
        displayTaskPriority.text = ""

        // Hide the task card
        taskCard.visibility = View.GONE
        Toast.makeText(this, "Task successfully deleted", Toast.LENGTH_SHORT).show()
    }
}


// Show a toast message


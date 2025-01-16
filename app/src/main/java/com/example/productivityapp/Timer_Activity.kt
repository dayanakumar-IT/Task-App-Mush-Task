package com.example.productivityapp

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Timer_Activity : AppCompatActivity() {
    private var timerDurationInMillis: Long = 0L
    private var countDownTimer: CountDownTimer? = null
    private var isRunning = false
    private lateinit var timerText: TextView
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var resetButton: Button
    private lateinit var setTimerButton: Button
    private lateinit var hoursInput: EditText
    private lateinit var minutesInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        // Initialize views
        timerText = findViewById(R.id.timer_text)
        startButton = findViewById(R.id.start_button)
        stopButton = findViewById(R.id.stop_button)
        resetButton = findViewById(R.id.reset_button)
        setTimerButton = findViewById(R.id.set_timer_button)
        hoursInput = findViewById(R.id.hours_input)
        minutesInput = findViewById(R.id.minutes_input)

        // Handle Set Timer button
        setTimerButton.setOnClickListener {
            setTimer()
        }

        // Handle Start button
        startButton.setOnClickListener {
            if (timerDurationInMillis > 0L && !isRunning) {
                startTimer(timerDurationInMillis)
            } else {
                Toast.makeText(this, "Please set the timer first", Toast.LENGTH_SHORT).show()
            }
        }

        // Handle Stop button
        stopButton.setOnClickListener {
            stopTimer()
        }

        // Handle Reset button
        resetButton.setOnClickListener {
            resetTimer()
        }
    }

    // Function to set the timer from inputs
    private fun setTimer() {
        val hours = hoursInput.text.toString().toIntOrNull() ?: 0
        val minutes = minutesInput.text.toString().toIntOrNull() ?: 0
        timerDurationInMillis = (hours * 3600 + minutes * 60) * 1000L
        updateTimerText(timerDurationInMillis)
    }

    // Function to start the timer
    private fun startTimer(durationInMillis: Long) {
        countDownTimer = object : CountDownTimer(durationInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                isRunning = true
                updateTimerText(millisUntilFinished)
            }

            override fun onFinish() {
                isRunning = false
                Toast.makeText(this@Timer_Activity, "Timer Finished", Toast.LENGTH_SHORT).show()
                resetTimer()
            }
        }.start()
    }

    // Function to stop the timer
    private fun stopTimer() {
        countDownTimer?.cancel()
        isRunning = false
    }

    // Function to reset the timer
    private fun resetTimer() {
        countDownTimer?.cancel()
        timerDurationInMillis = 0L
        timerText.text = "00:00:00"
        isRunning = false
    }

    // Function to update the timer text
    private fun updateTimerText(millisUntilFinished: Long) {
        val hours = millisUntilFinished / (1000 * 60 * 60) % 24
        val minutes = millisUntilFinished / (1000 * 60) % 60
        val seconds = millisUntilFinished / 1000 % 60

        timerText.text = String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}

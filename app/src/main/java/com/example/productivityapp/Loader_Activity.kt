package com.example.productivityapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Loader_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_loader)

        // Apply edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Delay for 4 seconds (4000 milliseconds), then start the next activity
        Handler(Looper.getMainLooper()).postDelayed({
            // Intent to start the next activity
            val intent = Intent(this, Intro_Activity::class.java)
            startActivity(intent)
            finish() // Close this activity so the user can't return to it
        }, 3500) // 4000 milliseconds = 4 seconds
    }
}

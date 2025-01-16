package com.example.productivityapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Onboarding3_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_onboarding3)

        val gbutton: Button = findViewById(R.id.buttongroovy)
        val hbutton: Button = findViewById(R.id.buttonhappy)

        gbutton.setOnClickListener {
            // Start the new activity
            val intent = Intent(this, Nameinput_Activity::class.java) // Replace TargetActivity with your desired activity class
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }

        hbutton.setOnClickListener {
            // Start the new activity
            val intent = Intent(this, Nameinput_Activity::class.java) // Replace TargetActivity with your desired activity class
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }

    }
}
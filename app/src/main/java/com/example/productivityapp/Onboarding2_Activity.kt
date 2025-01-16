package com.example.productivityapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Onboarding2_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_onboarding2)

        // Find the TextView by its ID
        val textView = findViewById<TextView>(R.id.noted_onb)
        val textView2 = findViewById<TextView>(R.id.ok_onb)

        // Set an OnClickListener to the TextView
        textView.setOnClickListener {
            // Create an Intent to navigate to NextActivity
            val intent = Intent(this, Onboarding3_Activity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        // Set an OnClickListener to the TextView2
        textView2.setOnClickListener {
            // Create an Intent to navigate to NextActivity
            val intent = Intent(this, Onboarding3_Activity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }
}
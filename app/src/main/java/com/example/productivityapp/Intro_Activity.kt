package com.example.productivityapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable

class Intro_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_intro)

        val lottieAnimationView = findViewById<LottieAnimationView>(R.id.lottieAnimationView)

        // Set custom background programmatically
        val button: Button = findViewById(R.id.getstart_intro_btn)
        button.background = ContextCompat.getDrawable(this, R.drawable.ripple_effect)

        // Set an OnClickListener for the button
        button.setOnClickListener {
            // Start the new activity
            val intent = Intent(this, Onboarding1_Activity::class.java) // Replace TargetActivity with your desired activity class
            startActivity(intent)
            finish() // Optional: call finish() if you want to close the current activity
        }

    }
}
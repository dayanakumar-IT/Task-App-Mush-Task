package com.example.productivityapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Nameinput_Activity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var continuenameinput: Button
    lateinit var entername: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nameinput)

        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        // Find views by ID
        continuenameinput = findViewById(R.id.continuenameinput)
        entername = findViewById(R.id.entername)

        // Set button click listener
        continuenameinput.setOnClickListener {
            val name: String = entername.text.toString()
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("NAME", name)
            editor.apply()

            val intent = Intent(this, Addtask_Activity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }
    }
}




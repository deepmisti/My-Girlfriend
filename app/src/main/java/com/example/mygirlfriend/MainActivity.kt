package com.example.mygirlfriend

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val messages = listOf(
        "You make every single day brighter! ✨",
        "Just a reminder: You are deeply loved and appreciated. ❤️",
        "Thinking of your beautiful smile right now! 🥰",
        "You are my favorite notification in this busy life. 💌",
        "Every love story is beautiful, but ours is my favorite. 🌹"
    )
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvMessage = findViewById<TextView>(R.id.tvMessage)
        val btnNext = findViewById<Button>(R.id.btnNext)

        btnNext.setOnClickListener {
            currentIndex = (currentIndex + 1) % messages.size
            tvMessage.text = messages[currentIndex]
        }
    }
}

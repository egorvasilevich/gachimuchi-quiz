package com.example.billy

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val usernameTextLabel: TextView = findViewById(R.id.username_text_view)
        val resultTextLabel: TextView = findViewById(R.id.score_text_view)
        val finishButton: Button = findViewById(R.id.finish_button)

        val username = intent.getStringExtra(Constants.USER_NAME)
        val totalQuestions = intent.getStringExtra(Constants.TOTAL_QUESTIONS)
        val correctAnswers = intent.getStringExtra(Constants.CORRECT_ANSWERS)

        finishButton.text = "FINISH"
        usernameTextLabel.text = username
        resultTextLabel.text = "You have earned ${correctAnswers.toString()}/${totalQuestions.toString()} points!"
    }
}
package com.example.billy

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text
import kotlin.math.roundToInt

class ResultActivity : AppCompatActivity(), View.OnClickListener {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val usernameTextView: TextView = findViewById(R.id.result_view_username_text)
        val scoreTextView: TextView = findViewById(R.id.result_view_score_text)
        val restartButton: Button = findViewById(R.id.result_view_restart_button)
        val markUserTextView: TextView = findViewById(R.id.result_view_description)

        val username = intent.getStringExtra(Constants.USER_NAME).toString()
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 10)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        usernameTextView.text = "♂$username♂"
        scoreTextView.text = "Ты набрал $correctAnswers из $totalQuestions очков!"

        markUserTextView.text = Constants.RESULTS[((correctAnswers*10)/totalQuestions.toDouble()).roundToInt()]
        restartButton.text = "ЗАНОВО"

        restartButton.setOnClickListener(this)
    }
    override fun onClick(v: View?){
        when (v?.id){
            R.id.result_view_restart_button -> {
                val username = intent.getStringExtra(Constants.USER_NAME)
//                Log.i("VAR", "onClick: username: $username")
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(Constants.USER_NAME, username)
                startActivity(intent)
                finish()
            }
        }
    }
}

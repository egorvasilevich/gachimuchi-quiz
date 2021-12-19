package com.example.billy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nameEdit: AppCompatEditText = findViewById(R.id.main_screen_name_edit)
        val startButton: Button = findViewById(R.id.button_start)
        startButton.setOnClickListener{
            if (nameEdit.text.toString().isEmpty()){
                Toast.makeText(this, "Введи имя, slave", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, QuestionActivity::class.java)
                intent.putExtra(Constants.USER_NAME, nameEdit.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}
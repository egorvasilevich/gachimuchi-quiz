package com.example.billy

import android.content.Intent
import android.graphics.PointF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Получение элементов поля
        val sStartButton: Button = findViewById(R.id.main_activity_start_button)
        val sNameEdit: AppCompatEditText = findViewById(R.id.main_activity_name_edit)

        // Установка прошлого имени пользователя, если мы пришли с финального экрана
        val previousUsername = intent.getStringExtra(Constants.USER_NAME)
        if (previousUsername.toString().isNotEmpty() && previousUsername.toString() != "null") {
            sNameEdit.setText(previousUsername.toString())
        }
        // Установка рандомного имени из списка
        else {
            sNameEdit.setText(Constants.NAMES[Random.nextInt(0,Constants.NAMES.size-1)])
        }

        // Обработка поведения при нажатии на кнопку Start
        sStartButton.setOnClickListener {
            val insertedName: String = sNameEdit.text.toString()
            when {
                insertedName.isEmpty() -> {
                    Toast.makeText(this, "Введи своё имя, ♂slave♂", Toast.LENGTH_SHORT).show()
                }
                insertedName.length <= 3 -> {
                    Toast.makeText(this, "Имя слишком короткое, ♂slave♂", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    // Переход на другое Activity, если имя прошло все проверки
                    val intent = Intent(this, QuestionActivity::class.java)
                    // Проброс полей для Activity с результатом
                    intent.putExtra(Constants.USER_NAME, sNameEdit.text.toString())
                    startActivity(intent)

//                    finish()
                }
            }
        }
    }
}
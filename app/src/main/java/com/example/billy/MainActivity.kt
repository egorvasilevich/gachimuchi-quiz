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
        val sNumberOfQuestionsEdit: AppCompatEditText = findViewById(R.id.main_activity_number_of_questions)

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

            var nameChecked = false
            var numberOfQuestionsChecked = false

            // Проверка имени
            val insertedName: String = sNameEdit.text.toString()
            when {
                insertedName.isEmpty() -> {
                    Toast.makeText(this, Constants.UNDEFINED_NAME_TAUNTS[Random.nextInt(0, Constants.UNDEFINED_NAME_TAUNTS.size)], Toast.LENGTH_SHORT).show()
                }
                insertedName.length < 2 -> {
                    Toast.makeText(this, Constants.SHORT_NAME_TAUNTS[Random.nextInt(0, Constants.SHORT_NAME_TAUNTS.size-1)], Toast.LENGTH_SHORT).show()
                }
                else -> {
                    nameChecked = true
                }
            }

            // Проверка количества вопросов
            val insertedNumberOfQuestions: String = sNumberOfQuestionsEdit.text.toString()
            when  (val numberOfQuestions: Int? = insertedNumberOfQuestions.toIntOrNull()){
                null -> {
                    Toast.makeText(this, Constants.INCORRECT_NUMBER_OF_QUESTIONS_TAUNTS[Random.nextInt(0,Constants.INCORRECT_NUMBER_OF_QUESTIONS_TAUNTS.size)], Toast.LENGTH_SHORT).show()
                }
                else -> {
                    if (numberOfQuestions < 3 || numberOfQuestions > Constants.TOTAL_QUESTIONS.length){
                        Toast.makeText(this, Constants.INCORRECT_NUMBER_OF_QUESTIONS_TAUNTS[Random.nextInt(0,Constants.INCORRECT_NUMBER_OF_QUESTIONS_TAUNTS.size)], Toast.LENGTH_SHORT).show()
                    } else {
                        numberOfQuestionsChecked = true
                    }
                }
            }
            if (numberOfQuestionsChecked && nameChecked){
                val intent = Intent(this, QuestionActivity::class.java)
                intent.putExtra(Constants.USER_NAME, sNameEdit.text.toString())
                intent.putExtra(Constants.QUESTIONS_COUNT, sNumberOfQuestionsEdit.text.toString().toInt())
                startActivity(intent)
                finish()
            }
        }
    }
}
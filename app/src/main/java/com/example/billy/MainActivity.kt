package com.example.billy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText

class MainActivity : AppCompatActivity() {

    // Список элементов на поле. Достучаться по ID не получается, поэтому
    // используется внутренняя функция findViewById()
    private var sStartButton: Button = findViewById(R.id.main_activity_start_button)
    private var sNameEdit: AppCompatEditText = findViewById(R.id.main_activity_name_edit)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Обработка поведения при нажатии на кнопку Start
        sStartButton!!.setOnClickListener {
            if (sNameEdit!!.text.toString().isEmpty()){
                Toast.makeText(this, "Введи своё имя, ♂slave♂", Toast.LENGTH_SHORT).show()
            } else {
                // Переход на другое Activity, если имя прошло все проверки
                val intent = Intent(this, QuestionActivity::class.java)
                // Проброс полей для Activity с результатом
                intent.putExtra(Constants.USER_NAME, sNameEdit!!.text.toString())
            }
        }
    }

    private fun initializeFields(){
        sStartButton = findViewById(R.id.main_activity_start_button)
        sNameEdit = findViewById(R.id.main_activity_name_edit)
    }
}
package com.example.billy

import android.util.Log
import kotlin.collections.ArrayList

object Constants{

    const val USER_NAME: String = "username"
    const val TOTAL_QUESTIONS: String = "total_question"
    const val CORRECT_ANSWERS: String = "correct_answers"

    private val QUESTIONS_POOL: ArrayList<Question> = arrayListOf(
        Question(
            0,
            "Какая фамилия у ♂Billy♂?",
            R.drawable.billy_profile,
            listOf("Herrington", "Prizm", "Raulo", "Moritz"),
            1
        ),
        Question(
            1,
            "В каком году ♂Billy♂ был рождён?",
            R.drawable.billy_profile,
            listOf("1959", "1962", "1969", "1968"),
            3
        ),
        Question(
            2,
            "Какому знаку зодиака принадлежит ♂Van♂?",
            R.drawable.van_profile,
            listOf("Лев", "В♂yes♂ы", "Скорпион", "Рак"),
            3
        ),
        Question(
            3,
            "Какая настоящая фамилия у ♂Super-Yakuza?",
            R.drawable.danny_lee_profile,
            listOf("Lee", "Resko", "Virri", "Kombly"),
            2
        )
    )

    fun getQuestions(numberOfQuestions: Int): List<Question> {
        // Чтобы избежать копирования вопросов - будем выбирать рандомные
        // и удалять выбранные из общего массива вопросов
        val questionPool: List<Question> = QUESTIONS_POOL.shuffled()
        Log.i("Size", "getQuestions: size of questions pool: ${questionPool.size}")
        return questionPool.shuffled().slice(0 until numberOfQuestions)
    }
}
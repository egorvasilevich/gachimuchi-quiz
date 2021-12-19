package com.example.billy

import android.util.Log
import kotlin.collections.ArrayList

object Constants{

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_question"
    const val CORRECT_ANSWERS: String = "correct_answers"

    val RESULTS = mapOf<Int, String>(
        1 to "Ужасные результаты, slave. Старайся лучше",
        2 to "Плохой результат, master недоволен",
        3 to "С таким результатом в gym не пустят",
        4 to "Ты кое-что понимаешь в fisting. Но нужно глубже",
        5 to "Gym уже готов принять тебя",
        6 to "Boys возьмут тебя в следующий dungeon",
        7 to "С такими знаниям, boss of the gym - твой друг",
        8 to "Sorry for what - про тебя",
        9 to "Dungeon master - это ты",
        10 to "Ты знаешь всё. Может уже выпутишь новый фильм?"

    )

    val NAMES: ArrayList<String> = arrayListOf(
        "Billy",
        "Yakuza",
        "Letherman",
        "Artist",
        "Super-Yakuza",
        "Van",
        "Dan"
    )

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
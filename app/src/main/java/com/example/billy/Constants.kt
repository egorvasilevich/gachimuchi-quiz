package com.example.billy

import com.beust.klaxon.Klaxon

//TODO: Сделать это красивым, а не харкодить
object Constants{

    const val USER_NAME: String = "username"
    const val TOTAL_QUESTIONS: String = "total_question"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): ArrayList<Question>{
        val questionsList = ArrayList<Question>()
        val q1 = Question(
            1,
            "What second name on Billy",
            R.drawable.billy_profile,
            "Herington",
            "Prizm",
            "Raulo",
            "Moritz",
            1
        )
        questionsList.add(0,q1)
        val q2 = Question(
            2,
            "What age Billy was born",
            R.drawable.billy_profile,
            "1959",
            "1962",
            "1969",
            "1998",
            3
        )
        questionsList.add(1,q2)
        val q3 = Question(
            3,
            "What Zodiac sign Van has",
            R.drawable.van_profile,
            "Leo",
            "Gemini",
            "Virgo",
            "Scorpio",
            4
        )
        questionsList.add(2,q3)
        val q4 = Question(
            4,
            "What second name Super-Kazuya has",
            R.drawable.danny_lee_profile,
            "Lee",
            "Resko",
            "Virri",
            "Kombly",
            2
        )
        questionsList.add(3,q4)
        return questionsList
    }
}
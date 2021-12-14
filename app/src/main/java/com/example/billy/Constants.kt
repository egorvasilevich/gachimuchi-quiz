package com.example.billy

import com.beust.klaxon.Klaxon

object Constants{
    fun getQuestions(): ArrayList<Question>{
        val questionsList = ArrayList<Question>()
        val q1 = Question(
            1,
            "What second name on Billy",
            R.drawable.billy_background,
            "Herington",
            "Prizm",
            "Raulo",
            "Moritz",
            0
        )
        questionsList.add(0,q1)
        return questionsList
    }
}
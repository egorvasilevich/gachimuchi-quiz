package com.example.billy

import kotlin.collections.ArrayList

object Constants{

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_question"
    const val CORRECT_ANSWERS: String = "correct_answers"
    const val QUESTIONS_COUNT: String = "questions_count"

    val RESULTS = mapOf<Int, String>(
        0 to "Отвратительно. ♂Van♂ лично грозится опозорить тебя",
        1 to "Ужасные результаты, ♂slave♂. Старайся лучше",
        2 to "Плохой результат, ♂master♂ недоволен",
        3 to "С таким результатом в ♂gym♂ не пустят",
        4 to "Ты кое-что понимаешь в ♂fisting♂. Но нужно глубже",
        5 to "♂Gym♂ уже готов принять тебя",
        6 to "♂Boys♂ возьмут тебя в следующий ♂dungeon♂",
        7 to "С такими знаниям, ♂boss of the gym♂ - твой друг",
        8 to "♂Sorry for what♂ - про тебя",
        9 to "♂Dungeon master♂ - это ты",
        10 to "Ты знаешь всё. Может уже выпуcтишь новый фильм?"

    )

    val SHORT_NAME_TAUNTS = listOf(
        "Имя слишком короткое, ♂Slave♂",
        "♂Slave♂ не может иметь такое короткое имя",
        "Ты не можешь зайти в ♂gym♂ с таким коротким"
    )

    val UNDEFINED_NAME_TAUNTS = listOf(
        "♂Slave♂, ты не указал имя",
        "♂Billy♂ не будет знакомиться с безымянными",
        "♂Van♂ будет оскорблён тобой. Ему нужно твоё имя"
    )

    val INCORRECT_NUMBER_OF_QUESTIONS_TAUNTS = listOf(
        "Мы не можем дать столько вопросов, ♂slave♂",
        "♂Steve♂ не хочет, чтобы ты решал такое количество вопросов"
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
            "Какая фамилия у ♂Billy♂?",
            R.drawable.billy_profile_1,
            listOf("Herrington", "Prizm", "Raulo", "Moritz"),
            1
        ),
        Question(
            "В каком году ♂Billy♂ был рождён?",
            R.drawable.billy_profile_1,
            listOf("1959", "1962", "19♂69♂", "1968"),
            3
        ),
        Question(
            "Какому знаку зодиака принадлежит ♂Van♂?",
            R.drawable.van_profile_1,
            listOf("Лев", "В♂yes♂ы", "Скорпион", "Рак"),
            3
        ),
        Question(
            "Какая настоящая фамилия у ♂Super-Yakuza?",
            R.drawable.danny_lee_profile,
            listOf("Lee", "Resko", "Virri", "Kombly"),
            2
        ),
        Question(
            "Укажите город рождения ♂Brad♂",
            R.drawable.brad_profile,
            listOf("Талл♂in♂", "Париж", "Рим", "Чика♂go♂"),
            4
        ),
        Question(
            "Укажите фильм в котором НЕ снимался ♂Brad♂",
            R.drawable.brad_profile,
            listOf("Lord Of The Lockerroom","Plantin' Seed", "Up The Gut", "Park & Ride"),
            1
        ),
        Question(
            "Укажите год выхода фильма Lords Of The Lockerroom",
            R.drawable.lords_of_the_lockerroom_poster,
            listOf("1999", "1991", "1996","2002"),
            1
        ),
        Question(
            "Фраза с началом \"Lets celebrate and suck some...\" появляется в фильме",
            R.drawable.score_poster,
            listOf("Big Guns 2", "Boy Band", "♂Cock♂pit", "Score"),
            4
        ),
        Question(
            "В ходе сцены в лесу, когда была произнесена фраза \"Oh shit I'm sorry\", чего хотел ♂Steve♂ от ♂Brad♂ (фильм Boy Band)?",
            R.drawable.boy_band_shot_1,
            listOf("Позвонить", "Узнать погоду", "Купить припасы", "Познакомиться"),
            1,
        ),
        Question(
            "В какое время позвонил ♂Richard♂ ♂Steve♂ в фильме CatalinaVille?",
            R.drawable.catalinaville_shot_1,
            listOf("1:00","1:15", "1:30", "1:45"),
            3
        ),
        Question(
            "Какой был номер у пожарной части ♂Billy♂ в фильме Игра с огнём?",
            R.drawable.playing_with_fire_shot_1,
            listOf("69", "777", "1", "32"),
            1
        ),
        Question(
            "Японское слово 'гатимути' означает",
            R.drawable.gachi_actors_shot_1,
            listOf("Великий чемпион", "Успешный мужчина", "Мускулистый боец", "Накачанный здоровяк"),
            4
        ),
        Question(
            "Фраза \"Ass we can\" была сказана на мероприятии по продвижению сайта...",
            R.drawable.billy_profile_2,
            listOf("Dora Dura", "Nico Nico", "Bellic Bellic", "Kira Koma"),
            2
        ),
        Question(
            "Фраза \"Ass we can\" была сказана Billy во время его первого визита в ...",
            R.drawable.billy_profile_2,
            listOf("Корею", "Тайланд", "Вьетнам", "Японию"),
            4
        ),
        Question(
            "Продолжите фразу \"I was gonna be a movie star y'know,...\"",
            R.drawable.van_profile_2,
            listOf("spanking and screaming", "modelling and acting", "searching and finding", "drinking and relaxing"),
            2
        )
    )

    val GOOD_ANSWER_SOUND_POOL: ArrayList<Sound> = arrayListOf(
        Sound(
            0,
            R.raw.alright_man
        ),
        Sound(
            1,
            R.raw.dungeon_master
        ),
        Sound(
            2,
            R.raw.nice_ass
        ),
        Sound(
            3,
            R.raw.work_that_tool
        ),
        Sound(
            4,
            R.raw.yeeees
        ),
        Sound(
            5,
            R.raw.yah_bro
        ),
        Sound(
            6,
            R.raw.yeah_smartass
        ),
        Sound(
            7,
            R.raw.yeah_uhhhhhh
        ),
        Sound(
            8,
            R.raw.yeaaaauuuuuuuuuuuuuuuuh
        )
    )

    val BAD_ANSWER_SOUND_POOL: ArrayList<Sound> = arrayListOf(
        Sound(
            0,
            R.raw.aaaaaaaaaaaaaaaaaaaa
        ),
        Sound(
            1,
            R.raw.aaaaaaaaaaaaaah
        ),
        Sound(
            2,
            R.raw.boy_next_door
        ),
        Sound(
            3,
            R.raw.come_on_college_boy
        ),
        Sound(
            4,
            R.raw.come_on_lets_go
        ),
        Sound(
            5,
            R.raw.come_on_son
        ),
        Sound(
            6,
            R.raw.come_ooooon
        ),
        Sound(
            7,
            R.raw.dont_stop
        ),
        Sound(
            8,
            R.raw.take_it_boy
        )
    )

    fun getQuestions(numberOfQuestions: Int): List<Question> {
        val questionPool: List<Question> = QUESTIONS_POOL.shuffled()
        return questionPool.shuffled().slice(0 until numberOfQuestions)
    }

    fun getQuestionsPoolSize(): Int{
        return QUESTIONS_POOL.size
    }

}
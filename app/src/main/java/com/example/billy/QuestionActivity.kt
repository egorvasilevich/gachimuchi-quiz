package com.example.billy

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import android.media.MediaPlayer
import java.lang.Exception
import kotlin.random.Random


class QuestionActivity : AppCompatActivity(), View.OnClickListener {
    // Штука для проигрывания звуков
    var mMediaPlayer: MediaPlayer? = null
    // Номер нынешнего вопросв
    private var mCurrentPosition: Int = 1
    // Список вопросов на данный запуск квиза
    private var mQuestionsList: List<Question>? = null
    // Выбранный ответ
    private var mSelectedOption: Int = 0
    // Количество правильных ответов
    private var mCorrectAnswers: Int = 0
    // Количество вопросов в запуске квиза
    private var mTotalQuestions: Int = 10


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        val submitButton = findViewById<Button>(R.id.questions_activity_submit_button)
        val progressBar = findViewById<ProgressBar>(R.id.questions_activity_progress_bar)

        mQuestionsList = Constants.getQuestions(mTotalQuestions)
        // Установка вопроса на экран
        setQuestion()

        // Настройка кнопок выбора ответа
        getOptionsTextViews().forEach { choiceButton ->
            choiceButton.setOnClickListener(this)
        }
        submitButton.setOnClickListener(this)

        progressBar.max = mTotalQuestions
    }

    private fun getOptionsTextViews(): ArrayList<TextView> {
        return arrayListOf(
            findViewById(R.id.questions_activity_answer_option_one),
            findViewById(R.id.questions_activity_answer_option_two),
            findViewById(R.id.questions_activity_answer_option_three),
            findViewById(R.id.questions_activity_answer_option_four)
        )
    }

    // Функция обновляет экран, добавляя картинку, текст, обновляя ProgressBar для нового вопроса и кнопку принятия решения
    private fun setQuestion(){
        val progressBar = findViewById<ProgressBar>(R.id.questions_activity_progress_bar)
        val progressBarText = findViewById<TextView>(R.id.questions_activity_progress_text)
        val imageQuestion = findViewById<ImageView>(R.id.questions_activity_question_image)
        val textQuestion = findViewById<TextView>(R.id.questions_activity_question_text)
        val answerButton = findViewById<Button>(R.id.questions_activity_submit_button)

        val currentQuestion = mQuestionsList!![mCurrentPosition-1]

        // Обновление вида всех кнопок на дефолтный
        defaultOptionsView()

        // Обновление кнопки
        if (mCurrentPosition == mQuestionsList!!.size){
            answerButton!!.text = "ЗАВЕРШИТЬ"
        }
        answerButton.text = "ОТВЕТИТЬ"

        // Обновление ProgressBar и текста рядом
        progressBar.progress = mCurrentPosition
        "${mCurrentPosition}/${progressBar.max}".also { progressBarText.text = it }

        // Установка вопроса, картинки, вариантов ответа
        textQuestion.text = currentQuestion.question
        imageQuestion.setImageResource(currentQuestion.image)
//        for (i in iOptionsTextViews!!.indices) iOptionsTextViews!!.elementAt(i).text = currentQuestion.options[i]
        val optionViews = getOptionsTextViews()
        for (i in 0 until optionViews.size){
            optionViews.elementAt(i).text = currentQuestion.options[i]
        }
    }

    // Установка вида кнопок на невыбранный вариант ответа
    private fun defaultOptionsView(){
        val options = getOptionsTextViews()
        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
            if (mMediaPlayer != null) {
                mMediaPlayer!!.stop()
                mMediaPlayer!!.release()
                mMediaPlayer = null
                }
            mMediaPlayer = MediaPlayer.create(this, R.raw.slap5)
            mMediaPlayer!!.isLooping = false
            mMediaPlayer!!.start()

        val answerButton = findViewById<Button>(R.id.questions_activity_submit_button)

        val options = getOptionsTextViews()
        when (v?.id){
            R.id.questions_activity_answer_option_one ->{
                selectedOptionView(options[0], 1)
            }
            R.id.questions_activity_answer_option_two ->{
                selectedOptionView(options[1], 2)
            }
            R.id.questions_activity_answer_option_three ->{
                selectedOptionView(options[2], 3)
            }
            R.id.questions_activity_answer_option_four ->{
                selectedOptionView(options[3], 4)
            }
            R.id.questions_activity_submit_button -> {
                if(mSelectedOption == 0){
                    mCurrentPosition++
                    when{
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion()
                        } else -> {
                            val username = intent.getStringExtra(Constants.USER_NAME).toString()
                            val intent = Intent(this, ResultActivity::class.java)
//                            Log.i("VAR", "onClick: correctAnswers: $mCorrectAnswers")
//                            Log.i("VAR", "onClick: username: $username")
//                            Log.i("VAR", "onClick: totalQuestions: $mTotalQuestions")
                            intent.putExtra(Constants.USER_NAME, username)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mTotalQuestions)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)
                    val correctAnswer = question!!.correctAnswer
                    if  (mSelectedOption != correctAnswer) {
                        answerView(mSelectedOption, R.drawable.fail_option_border_bg)
                        if (mMediaPlayer != null) {
                            mMediaPlayer!!.stop()
                            mMediaPlayer!!.release()
                            mMediaPlayer = null
                        }
                        mMediaPlayer = MediaPlayer.create(this, Constants.BAD_ANSWER_SOUND_POOL[Random.nextInt(0,Constants.BAD_ANSWER_SOUND_POOL.size-1)].sound)
                        mMediaPlayer!!.isLooping = false
                        mMediaPlayer!!.start()
                    } else {
                        mCorrectAnswers++
                        if (mMediaPlayer != null) {
                            mMediaPlayer!!.stop()
                            mMediaPlayer!!.release()
                            mMediaPlayer = null
                        }
                        mMediaPlayer = MediaPlayer.create(this, Constants.GOOD_ANSWER_SOUND_POOL[Random.nextInt(0,Constants.GOOD_ANSWER_SOUND_POOL.size-1)].sound)
                        mMediaPlayer!!.isLooping = false
                        mMediaPlayer!!.start()
                    }
                    answerView(correctAnswer, R.drawable.correct_option_border_bg )
                    if (mCurrentPosition == mQuestionsList!!.size) {
                        answerButton!!.text = "ЗАВЕРШИТЬ"
                    } else {
                        answerButton!!.text = "СЛЕДУЮЩИЙ ВОПРОС"
                    }
                    mSelectedOption = 0
                }
            }
        }
    }

    private fun answerView (answer: Int, drawableView: Int){
        val options = getOptionsTextViews()
        options[answer-1].background = ContextCompat.getDrawable(this, drawableView)
    }

    private fun selectedOptionView(tv: TextView?, selectedOptionNumber: Int){
        defaultOptionsView()
        mSelectedOption = selectedOptionNumber
        tv!!.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )

    }
}
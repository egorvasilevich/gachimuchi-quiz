package com.example.billy

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuestionActivity : AppCompatActivity(), View.OnClickListener {

    // Номер нынешнего вопросв
    private var mCurrentPosition: Int = 1
    // Список вопросов на данный запуск квиза
    private var mQuestionsList: List<Question>? = null
    // Выбранный ответ
    private var mSelectedOption: Int = 0
    // Количество правильных ответов
    private var mCorrectAnswers: Int = 0
    // Количество вопросов в запуске квиза
    private var mTotalQuestions: Int = 4


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        val answerButton = findViewById<Button>(R.id.answer_button)

        mQuestionsList = Constants.getQuestions(mTotalQuestions)
        // Установка вопроса на экран
        setQuestion()

        // Настройка кнопок выбора ответа
        getOptionsTextViews().forEach { choiceButton ->
            choiceButton.setOnClickListener(this)
        }
        answerButton.setOnClickListener(this)
    }

    private fun getOptionsTextViews(): ArrayList<TextView> {
        return arrayListOf(
            findViewById(R.id.answer_option_one),
            findViewById(R.id.answer_option_two),
            findViewById(R.id.answer_option_three),
            findViewById(R.id.answer_option_four)
        )
    }

    // Функция обновляет экран, добавляя картинку, текст, обновляя ProgressBar для нового вопроса и кнопку принятия решения
    private fun setQuestion(){
        val progressBar = findViewById<ProgressBar>(R.id.progress_details_bar)
        val progressBarText = findViewById<TextView>(R.id.progress_details_text)
        val imageQuestion = findViewById<ImageView>(R.id.image_question)
        val textQuestion = findViewById<TextView>(R.id.text_question)
        val answerButton = findViewById<Button>(R.id.answer_button)

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
        val answerButton = findViewById<Button>(R.id.answer_button)

        val options = getOptionsTextViews()
        when (v?.id){
            R.id.answer_option_one ->{
                selectedOptionView(options[0], 1)
            }
            R.id.answer_option_two ->{
                selectedOptionView(options[1], 2)
            }
            R.id.answer_option_three ->{
                selectedOptionView(options[2], 3)
            }
            R.id.answer_option_four ->{
                selectedOptionView(options[3], 4)
            }
            R.id.answer_button -> {
                if(mSelectedOption == 0){
                    mCurrentPosition++
                    when{
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion()
                        } else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, intent.getStringExtra(Constants.USER_NAME))
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
                    } else {
                        mCorrectAnswers++
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
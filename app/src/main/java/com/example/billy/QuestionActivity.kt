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
import org.w3c.dom.Text

class QuestionActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOption: Int = 0
    private var progressBar: ProgressBar? = null
    private var progressBarText: TextView? = null
    private var textQuestion: TextView? = null
    private var imageQuestion: ImageView? = null
    private var answerOptionOne: TextView? = null
    private var answerOptionTwo: TextView? = null
    private var answerOptionThree: TextView? = null
    private var answerOptionFour: TextView? = null
    private var answerButton: Button? = null
    private var mCorrectAnswers: Int = 0
    private var mUserName: String? = null

    private var mTotalQuestions: Int = 10



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        progressBar = findViewById(R.id.progress_details_bar)
        progressBarText = findViewById(R.id.progress_details_text)
        textQuestion = findViewById(R.id.text_question)
        imageQuestion = findViewById(R.id.image_question)
        answerOptionOne = findViewById(R.id.answer_option_one)
        answerOptionTwo = findViewById(R.id.answer_option_two)
        answerOptionThree = findViewById(R.id.answer_option_three)
        answerOptionFour = findViewById(R.id.answer_option_four)
        answerButton = findViewById(R.id.answer_button)

        //TODO: Заменить на выбор mTotalQuestions рандомных вопросов
        mQuestionsList = Constants.getQuestions()
        setQuestion()

        answerOptionOne!!.setOnClickListener(this)
        answerOptionTwo!!.setOnClickListener(this)
        answerOptionThree!!.setOnClickListener(this)
        answerOptionFour!!.setOnClickListener(this)

        answerButton!!.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion(){
        val question = mQuestionsList!![mCurrentPosition-1]
        defaultOptionsView()

        if (mCurrentPosition == mQuestionsList!!.size){
            answerButton!!.text = "FINISH"
        }

        progressBar!!.progress = mCurrentPosition
        progressBarText!!.text = "$mCurrentPosition" +"/" + progressBar!!.max

        textQuestion!!.text = question.question
        imageQuestion!!.setImageResource(question.image)
        answerOptionOne!!.text = question.optionOne
        answerOptionTwo!!.text = question.optionTwo
        answerOptionThree!!.text = question.optionThree
        answerOptionFour!!.text = question.optionFour
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView?>()
        options.add(0, answerOptionOne)
        options.add(1, answerOptionTwo)
        options.add(2,answerOptionThree)
        options.add(3, answerOptionFour)
        for (option in options){
            option!!.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        when (v?.id){
            R.id.answer_option_one ->{
                selectedOptionView(answerOptionOne, 1)
            }
            R.id.answer_option_two ->{
                selectedOptionView(answerOptionTwo, 2)
            }
            R.id.answer_option_three ->{
                selectedOptionView(answerOptionThree, 3)
            }
            R.id.answer_option_four ->{
                selectedOptionView(answerOptionFour, 4)
            }
            R.id.answer_button -> {
                if(mSelectedOption == 0){
                    mCurrentPosition++
                    when{
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion()
                        } else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
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
                        answerButton!!.text = "FINISH"
                    } else {
                        answerButton!!.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOption = 0
                }
            }
        }
    }

    private fun answerView (answer: Int, drawableView: Int){
        when(answer){
            1 -> {
                answerOptionOne!!.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2 -> {
                answerOptionTwo!!.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3 -> {
                answerOptionThree!!.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            4 -> {
                answerOptionFour!!.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
        }
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
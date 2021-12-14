package com.example.billy

import android.annotation.SuppressLint
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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        progressBar = findViewById(R.id.progress_details_bar)
        progressBarText = findViewById(R.id.progress_details_text)
        textQuestion = findViewById(R.id.text_question)
        imageQuestion = findViewById(R.id.image_question)
        answerOptionOne = findViewById(R.id.answer_option_one)
        answerOptionTwo = findViewById(R.id.answer_option_two)
        answerOptionThree = findViewById(R.id.answer_option_three)
        answerOptionFour = findViewById(R.id.answer_option_four)
        answerButton = findViewById(R.id.answer_button)

        mQuestionsList = Constants.getQuestions()
        setQuestion()

        answerOptionOne!!.setOnClickListener(this)
        answerOptionTwo!!.setOnClickListener(this)
        answerOptionThree!!.setOnClickListener(this)
        answerOptionFour!!.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion(){
        mCurrentPosition = 1
        val question = mQuestionsList!![mCurrentPosition-1]
        defaultOptionsView()

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
package com.yavuz.questionnaireapp

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class QuestionView
@JvmOverloads
constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0
) : LinearLayout(context, attrs) {
    private var questionTextView: TextView
    private var optionsLayout: LinearLayout
    var questionAdapter: QuestionAdapter

    init {

        LayoutInflater.from(context).inflate(R.layout.custom_view, this, true)
        orientation = VERTICAL // Set the orientation to vertical

        questionTextView = findViewById(R.id.questionTextView)
        optionsLayout = findViewById(R.id.optionsLayout)
        questionAdapter = QuestionAdapter(mutableListOf())

    }

    fun setQuestion(question: Question) {
        questionTextView.text = question.text
        Log.d("QuestionView", "Setting Question: $question")
        optionsLayout.removeAllViews()

        if (question.type == "single") {
            val radioGroup = RadioGroup(context)
            question.options?.forEach { option ->
                val radioButton = RadioButton(context)
                radioButton.text = option
                radioGroup.addView(radioButton)
            }
            optionsLayout.addView(radioGroup)
        } else if (question.type == "multiple") {
            question.options?.forEach { option ->
                val checkBox = CheckBox(context)
                checkBox.text = option
                optionsLayout.addView(checkBox)
            }
        }
    }
    fun setQuestions(questions: List<Question>) {
        removeAllViews()

        questions.forEach { question ->
            val questionView = QuestionView(context)
            questionView.setQuestion(question)
            addView(questionView)
        }
    }

}


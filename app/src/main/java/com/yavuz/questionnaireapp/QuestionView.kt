package com.yavuz.questionnaireapp

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView

class QuestionView
@JvmOverloads
constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0
) : LinearLayout(context, attrs) {
    private var questionTextView: TextView
    private var optionsLayout: LinearLayout

    init {

        LayoutInflater.from(context).inflate(R.layout.custom_view, this, true)
        orientation = VERTICAL

        questionTextView = findViewById(R.id.questionTextView)
        optionsLayout = findViewById(R.id.optionsLayout)

    }
    fun getUserAnswer(question: Question) {

        optionsLayout.removeAllViews()

        for (option in question.options!!) {
            if (question.type == "single") {
                val radioButton = RadioButton(context)
                radioButton.text = option
                optionsLayout.addView(radioButton)
            } else if (question.type == "multiple") {
                val checkBox = CheckBox(context)
                checkBox.text = option
                optionsLayout.addView(checkBox)
            }
        }
    }

    fun setQuestion(question: Question) {
      questionTextView.text = question.text
        getUserAnswer(question)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

         optionsLayout.measure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

         optionsLayout.layout(left, top, right, bottom)
    }
}

package com.yavuz.questionnaireapp

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.TextView

class QuestionView
@JvmOverloads
constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0
) : RelativeLayout(context, attrs) {
    private var questionTextView: TextView
    private var optionsLayout: LinearLayout
    var questionAdapter: QuestionAdapter

    init {

        LayoutInflater.from(context).inflate(R.layout.custom_view, this, true)

        questionTextView = findViewById(R.id.questionTextView)
        optionsLayout = findViewById(R.id.optionsLayout)
        questionAdapter = QuestionAdapter(questions = emptyList())
    }
    fun setQuestion(question: Question) {
        questionTextView.text = question.text
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
 /*   fun addQuestion(question: Question) {
        questionAdapter.updateItemAtPosition(0, question) // Güncelleme işlemi, eğer farklı bir indekse eklemek istiyorsanız onu belirtin
    }*/
}

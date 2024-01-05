package com.yavuz.questionnaireapp.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.yavuz.questionnaireapp.adapter.QuestionAdapter
import com.yavuz.questionnaireapp.databinding.CustomViewBinding
import com.yavuz.questionnaireapp.model.Answer
import com.yavuz.questionnaireapp.model.Question

interface SurveyCallback {
    fun onAnswerReceived(answer: List<Answer>)
}

class QuestionView
@JvmOverloads
constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    private val binding: CustomViewBinding = CustomViewBinding.inflate(LayoutInflater.from(context), this, true)

    private var surveyCallback: SurveyCallback? = null

    private val questionAdapter: QuestionAdapter = QuestionAdapter(emptyList(),
        object: SurveyCallback{
            override fun onAnswerReceived(answer: List<Answer>) {
                surveyCallback?.onAnswerReceived(answer)
            }
        })

    init {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = questionAdapter

    }

    fun setQuestions(questions: List<Question>) {
        questionAdapter.setQuestions(questions)
    }
    fun setSurveyCallback(callback: SurveyCallback) {
        surveyCallback = callback
    }
    fun setColor(viewBackGroundColor: Int, fontColor: Int) {
        binding.root.setBackgroundColor(ContextCompat.getColor(context, viewBackGroundColor))
        questionAdapter.setColorAdapter(ContextCompat.getColor(context, fontColor))
    }
}


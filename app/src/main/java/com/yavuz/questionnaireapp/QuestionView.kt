package com.yavuz.questionnaireapp

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.yavuz.questionnaireapp.databinding.CustomViewBinding

class QuestionView
@JvmOverloads
constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0
) : LinearLayout(context, attrs) {
    private val questionAdapter: QuestionAdapter
    private val binding: CustomViewBinding

    init {
        orientation = VERTICAL
        binding = CustomViewBinding.inflate(LayoutInflater.from(context), this, true)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        questionAdapter = QuestionAdapter(listOf())
        binding.recyclerView.adapter = questionAdapter
    }

    fun setQuestions(questions: List<Question>) {
        questionAdapter.setQuestions(questions)
    }
}


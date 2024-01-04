package com.yavuz.questionnaireapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.yavuz.questionnaireapp.databinding.ItemQuestionBinding
import com.yavuz.questionnaireapp.model.Answer
import com.yavuz.questionnaireapp.model.Question
import com.yavuz.questionnaireapp.view.SurveyCallback

class QuestionAdapter (
    private var question: List<Question>,
    private var surveyCallback: SurveyCallback
    ) :
    RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {
    private var userAnswers: ArrayList<Answer> = arrayListOf()
    private var questionTextColor: Int? = null

    inner class QuestionViewHolder(val itemQuestionBinding: ItemQuestionBinding) : RecyclerView.ViewHolder(itemQuestionBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemQuestionBinding = ItemQuestionBinding.inflate(inflater, parent, false)
        return QuestionViewHolder(itemQuestionBinding)
    }

    override fun getItemCount(): Int {
        return question.size
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val question = question[position]
        holder.itemQuestionBinding.questionTextView.text = question.text
        holder.itemQuestionBinding.explanationTextView.text = question.explanation

        questionTextColor?.let { holder.itemQuestionBinding.questionTextView.setTextColor(it) }


        if(!question.options.isNullOrEmpty()) {
            holder.itemQuestionBinding.singleChoiceRadioGroup.removeAllViews()
            holder.itemQuestionBinding.multipleChoiceLayout.removeAllViews()

            for (option in question.options!!) {
                if (question.type == "single") {
                    val radioButton = RadioButton(holder.itemView.context)
                    radioButton.text = option
                    radioButton.setOnCheckedChangeListener { _, isChecked ->
                        if (isChecked) {
                            val answer = Answer(question.id, option)
                            userAnswers.add(answer)
                        }
                    }

                    holder.itemQuestionBinding.singleChoiceRadioGroup.addView(radioButton)

                } else if (question.type == "multiple") {
                    val checkBox = CheckBox(holder.itemView.context)
                    checkBox.text = option
                    checkBox.setOnCheckedChangeListener { _, isChecked ->
                        if (isChecked) {
                            val answer = Answer(question.id, option)
                            userAnswers.add(answer)
                        }
                    }
                    holder.itemQuestionBinding.multipleChoiceLayout.addView(checkBox)
                }
            }
        }
        if (checkIfAllQuestionsAnswered()) {
            surveyCallback.onAnswerReceived(userAnswers)
        }


        Log.d("QuestionAdapter", "Binding question: ${question.text}")
        if(question.type == "single"){
            holder.itemQuestionBinding.singleChoiceRadioGroup.visibility = View.VISIBLE
            holder.itemQuestionBinding.multipleChoiceLayout.visibility = View.GONE
        } else {
            holder.itemQuestionBinding.singleChoiceRadioGroup.visibility = View.GONE
            holder.itemQuestionBinding.multipleChoiceLayout.visibility = View.VISIBLE
        }
    }

    private fun checkIfAllQuestionsAnswered(): Boolean {
        if (userAnswers.size == itemCount){
            surveyCallback.onAnswerReceived(userAnswers)
        }
        return true
    }

    fun setQuestions(questions: List<Question>) {
        this.question = questions
        notifyDataSetChanged()
    }

    fun setColorAdapter(fontColor: Int) {
        this.questionTextColor = fontColor
        notifyDataSetChanged()
    }
}
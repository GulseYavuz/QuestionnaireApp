package com.yavuz.questionnaireapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.yavuz.questionnaireapp.model.Question
import com.yavuz.questionnaireapp.databinding.ItemQuestionBinding


class QuestionAdapter (
    private var question: List<Question>
) :
    RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

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

        if (question.isRequired) {
            holder.itemQuestionBinding.requiredTextView.visibility = View.VISIBLE
        } else {
            holder.itemQuestionBinding.requiredTextView.visibility = View.GONE
        }

        holder.itemQuestionBinding.singleChoiceRadioGroup.removeAllViews()
        holder.itemQuestionBinding.multipleChoiceLayout.removeAllViews()
        if(!question.options.isNullOrEmpty()) {
            for (option in question.options!!) {
                if (question.type == "single") {
                    val radioButton = RadioButton(holder.itemView.context)
                    radioButton.text = option
                    holder.itemQuestionBinding.singleChoiceRadioGroup.addView(radioButton)
                } else if (question.type == "multiple") {
                    val checkBox = CheckBox(holder.itemView.context)
                    checkBox.text = option
                    holder.itemQuestionBinding.multipleChoiceLayout.addView(checkBox)
                }
            }
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
    fun setQuestions(questions: List<Question>) {
        this.question = questions
        notifyDataSetChanged()
    }
}
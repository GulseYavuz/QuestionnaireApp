package com.yavuz.questionnaireapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yavuz.questionnaireapp.databinding.ItemQuestionBinding

class QuestionAdapter (private val questions: List<Question>) :
    RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

        inner class QuestionViewHolder(val binding: ItemQuestionBinding) :
            RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val binding = ItemQuestionBinding.inflate(LayoutInflater.from(parent.context))
        return QuestionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val question = questions[position]
        holder.binding.questionTextView.text = question.text

        Log.d("QuestionAdapter", "Binding question: ${question.text}")
        if(question.type == "single"){
            holder.binding.singleChoiceRadioGroup.visibility = View.VISIBLE
            holder.binding.multipleChoiceLayout.visibility = View.GONE
        } else {
            holder.binding.singleChoiceRadioGroup.visibility = View.GONE
            holder.binding.multipleChoiceLayout.visibility = View.VISIBLE
        }
    }
}
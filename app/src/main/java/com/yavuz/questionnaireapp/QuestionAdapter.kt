package com.yavuz.questionnaireapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class QuestionAdapter (
    private var questions: List<Question>
) :
    RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionView: QuestionView = itemView as QuestionView

        fun bind(question: Question) {
            questionView.setQuestion(question)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.custom_view, parent, false)
        return QuestionViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val question = questions[position]
        holder.bind(question)
    }
/*    fun updateItemAtPosition(position: Int, updatedItem: Question) {
        questions[position] = updatedItem
        notifyItemChanged(position)
    }*/
}
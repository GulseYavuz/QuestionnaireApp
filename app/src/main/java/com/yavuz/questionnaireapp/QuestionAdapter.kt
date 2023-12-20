package com.yavuz.questionnaireapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class QuestionAdapter (
    private val questions: List<Question>
) :
    RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {


    inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(question: Question) {
            val questionView = itemView as QuestionView
            questionView.setQuestion(question)
        }    }

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


        /*    holder.itemView.singleChoiceRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = group.findViewById<RadioButton>(checkedId)
            val userAnswer = radioButton?.text.toString()

            userAnswerReceived.answerReceived(mapOf(question.id to userAnswer))
        }
        holder.itemView.multipleChoiceLayout.setOnClickListener {
            val checkedCheckBoxes = holder.itemView.multipleChoiceLayout.children.filterIsInstance<CheckBox>()
                .filter { it.isChecked }

            val userAnswers = checkedCheckBoxes.map { it.text.toString() }

            // Kullanıcının cevaplarını topla ve callback ile gönder
            userAnswerReceived.answerReceived(mapOf(question.id to userAnswers.joinToString()))
        }
        if(!question.options.isNullOrEmpty()) {
            for (option in question.options) {
                if (question.type == "single") {
                    val radioButton = RadioButton(holder.itemView.context)
                    radioButton.text = option
                    holder.itemView.singleChoiceRadioGroup.addView(radioButton)
                } else if (question.type == "multiple") {
                    val checkBox = CheckBox(holder.itemView.context)
                    checkBox.text = option
                    holder.itemView.multipleChoiceLayout.addView(checkBox)
                }

            }
        }*/

        /*      Log.d("QuestionAdapter", "Binding question: ${question.text}")
              if(question.type == "single"){
                  holder.binding.singleChoiceRadioGroup.visibility = View.VISIBLE
                  holder.binding.multipleChoiceLayout.visibility = View.GONE
              } else {
                  holder.binding.singleChoiceRadioGroup.visibility = View.GONE
                  holder.binding.multipleChoiceLayout.visibility = View.VISIBLE
              }*/
    }
}
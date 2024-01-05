package com.yavuz.questionnaireapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.yavuz.questionnaireapp.R
import com.yavuz.questionnaireapp.databinding.ActivityMainBinding
import com.yavuz.questionnaireapp.model.Answer
import com.yavuz.questionnaireapp.model.Questionnaire
import com.yavuz.questionnaireapp.view.SurveyCallback

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private lateinit var questionnaire: Questionnaire

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        questionnaire = loadQuestions()
        binding.questionView.setQuestions(questionnaire.questions)
        binding.questionView.setColor(R.color.turkuaz, R.color.black)

        val surveyCallback = object : SurveyCallback {
            override fun onAnswerReceived(answer: List<Answer>) {
                for (response in answer) {
                    Log.d("MainActivity", "Question ID: ${response.id}, User Response: ${response.text}")
                }
            }
        }
        binding.questionView.setSurveyCallback(surveyCallback)
    }

    fun loadQuestions(): Questionnaire {
        try {
            val inputStream = assets.open("question.json")
            val json = inputStream.bufferedReader().use { it.readText() }

            val parsedQuestionnaire = Gson().fromJson(json, Questionnaire::class.java)

            return parsedQuestionnaire ?: Questionnaire(mutableListOf(), "", "")
        } catch (e: Exception) {
            return questionnaire
        }
    }
}
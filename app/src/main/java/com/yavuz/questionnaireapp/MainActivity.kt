package com.yavuz.questionnaireapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.yavuz.questionnaireapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var questionnaire: Questionnaire


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        questionnaire = loadQuestions()
        binding.questionView.setQuestions(questionnaire.questions)

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
package com.yavuz.questionnaireapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.yavuz.questionnaireapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var questionnaire: Questionnaire
    private lateinit var questionView: QuestionView
    private lateinit var questionAdapter: QuestionAdapter
    private var currentQuestionIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        questionnaire = loadQuestions()
        questionAdapter = QuestionAdapter(emptyList())  // Initialize with an empty list

        binding.recyclerView.adapter = questionAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        questionView = findViewById(R.id.questionView)
        questionView.setQuestion(questionnaire.questions[0])
        questionView.questionAdapter = questionAdapter

    }

    fun loadQuestions(): Questionnaire {
        try {
            val inputStream = assets.open("question.json")
            val json = inputStream.bufferedReader().use { it.readText() }

            val parsedQuestionnaire = Gson().fromJson(json, Questionnaire::class.java)

            return parsedQuestionnaire ?: Questionnaire(emptyList(), "", "")
        } catch (e: Exception) {
            return questionnaire
        }
    }

}

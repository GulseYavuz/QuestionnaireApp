package com.yavuz.questionnaireapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.yavuz.questionnaireapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var questionAdapter: QuestionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadQuestions()
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        questionAdapter = QuestionAdapter(questions = emptyList())

        binding.recyclerView.adapter = questionAdapter


        Log.d("MainActivity", "Number of items in adapter: ${questionAdapter.itemCount}")

    }
    fun loadQuestions(): Questionnaire {
        val inputStream = assets.open("question.json")
        val json = inputStream.bufferedReader().use { it.readText() }
        Log.d("MainActivity", "JSON Data: $json")
        return Gson().fromJson(json, Questionnaire::class.java)
    }
}

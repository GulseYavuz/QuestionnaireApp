package com.yavuz.questionnaireapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.yavuz.questionnaireapp.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var questionAdapter: QuestionAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        questionAdapter = QuestionAdapter(emptyList())
        binding.recyclerView.adapter = questionAdapter
        Log.d("MainActivity", "Number of items in adapter: ${questionAdapter.itemCount}")

        fetchData()
    }
    private fun readLocalJson(): Questionnaire {
        try {
            val inputStream: InputStream = assets.open("question.json")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val json = String(buffer, Charsets.UTF_8)
            Log.d("JSON DATA", json)

            val gson = Gson()
            return gson.fromJson(json, Questionnaire::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            return Questionnaire(emptyList(), "", "")
        }
    }
    private fun fetchData() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val question = readLocalJson()

                withContext(Dispatchers.Main) {
                    questionAdapter = QuestionAdapter(question.questions)
                    binding.recyclerView.adapter = questionAdapter
                    questionAdapter.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("MainActivity", "Error fetching questions: ${e.message}")
            }
        }
    }
}
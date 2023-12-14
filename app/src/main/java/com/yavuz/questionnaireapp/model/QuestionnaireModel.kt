package com.yavuz.questionnaireapp.model

data class QuestionnaireModel(
    val description: String,
    val questions: List<QuestionX>,
    val title: String
)
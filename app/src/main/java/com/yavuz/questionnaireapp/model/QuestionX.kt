package com.yavuz.questionnaireapp.model

data class QuestionX(
    val explanation: String,
    val id: Int,
    val options: List<String>,
    val required: Boolean,
    val text: String,
    val type: String
)
package com.yavuz.questionnaireapp

data class UserAnswers(
    val questionId: Int,
    val answer: String
)
{

    companion object {
        var userAnswers: MutableList<UserAnswers>? = null
    }
}

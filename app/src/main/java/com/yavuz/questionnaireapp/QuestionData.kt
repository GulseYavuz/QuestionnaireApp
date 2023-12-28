package com.yavuz.questionnaireapp

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Questionnaire(
    @SerializedName("questions") val questions: MutableList<Question>,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String
) : Parcelable

@Parcelize
data class Question(
    @SerializedName("id") val id: Int,
    @SerializedName("text") var text: String,
    @SerializedName("explanation") var explanation: String,
    @SerializedName("type") val type: String,
    @SerializedName("required") val isRequired: Boolean,
    @SerializedName("options") var options: List<String>? = null
) :Parcelable
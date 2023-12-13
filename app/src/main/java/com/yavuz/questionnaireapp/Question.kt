package com.yavuz.questionnaireapp

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Questionnaire(
    @SerializedName("questions") val questions: List<Question>,
    @SerializedName("title") val title: String ,
    @SerializedName("description") val description: String
) : Parcelable

@Parcelize
data class Question(
    @SerializedName("id") val id: Int ,
    @SerializedName("text") val text: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("options") val options: List<String>? = null
) :Parcelable




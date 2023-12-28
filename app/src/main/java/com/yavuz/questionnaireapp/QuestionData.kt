package com.yavuz.questionnaireapp

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Questionnaire(
    @SerializedName("questions") val questionXES: MutableList<QuestionX>,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String
) : Parcelable

@Parcelize
data class QuestionX(
    @SerializedName("id") val id: Int,
    @SerializedName("text") var text: String,
    @SerializedName("type") val type: String,
    @SerializedName("options") var options: List<String>? = null
) :Parcelable



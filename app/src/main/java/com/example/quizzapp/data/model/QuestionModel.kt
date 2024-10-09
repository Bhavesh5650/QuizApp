package com.example.quizzapp.data.model

data class QuestionModel(
    val question:String,
    val answer:String,
    val optionList:MutableList<String?>?
)
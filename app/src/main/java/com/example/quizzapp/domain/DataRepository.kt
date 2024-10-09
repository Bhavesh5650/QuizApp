package com.example.quizzapp.domain

import android.provider.ContactsContract.Data
import com.example.quizzapp.data.helper.QuizApiHelper

class DataRepository {

    companion object{
        val repository = DataRepository()
    }

    val helper = QuizApiHelper()
    suspend fun getQuiz(category: String,difficulty:String)  = helper.quizApiCall(category = category, difficulty = difficulty)
}
package com.example.quizzapp.data.helper

import android.util.Log
import com.example.quizzapp.data.model.QuizModel
import com.example.quizzapp.data.network.QuizClient.Companion.getQuizApi
import com.example.quizzapp.data.network.QuizInterface
import retrofit2.awaitResponse

class QuizApiHelper {

    suspend fun quizApiCall(category: String,difficulty:String) : QuizModel?{

        val quizInterface = getQuizApi().create(QuizInterface::class.java)
        val response = quizInterface.getQuiz(category = category, difficulty = difficulty).awaitResponse()

        if(response.isSuccessful)
        {
            Log.d("Success", "quizApiCall: ====== ${response.body()} ")
            return response.body()
        }
        return null
    }
}
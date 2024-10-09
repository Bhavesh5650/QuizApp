package com.example.quizzapp.data.network

import com.example.quizzapp.data.model.QuizModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizInterface {

    @GET("api.php")
    fun getQuiz(
        @Query("amount") amount:String="10",
        @Query("category") category:String,
        @Query("difficulty") difficulty:String,
        @Query("type") type:String="multiple",
    ) : Call<QuizModel>
}
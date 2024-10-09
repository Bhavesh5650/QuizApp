package com.example.quizzapp.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuizClient {

    companion object{

        val URL = "https://opentdb.com/"
        private var retrofit:Retrofit?=null

        fun getQuizApi() : Retrofit
        {
            if(retrofit==null)
            {
                retrofit = Retrofit.Builder().baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit!!
        }

    }
}
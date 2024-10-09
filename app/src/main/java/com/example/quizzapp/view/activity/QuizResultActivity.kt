package com.example.quizzapp.view.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizzapp.R
import com.example.quizzapp.databinding.ActivityQuizResultBinding

class QuizResultActivity : AppCompatActivity() {

    private lateinit var binding:ActivityQuizResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityQuizResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getResultIntent()

    }

    private fun getResultIntent(){

        val correct = intent.getIntExtra("correct",0)
        val incorrect = intent.getIntExtra("incorrect",0)

        binding.setCorrectAnswer.text = correct.toString()
        binding.setIncorrectAnswer.text = incorrect.toString()
    }
}
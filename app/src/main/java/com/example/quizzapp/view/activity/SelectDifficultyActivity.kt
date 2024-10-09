package com.example.quizzapp.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizzapp.R
import com.example.quizzapp.databinding.ActivitySelectDifficultyBinding
import com.example.quizzapp.viewModel.QuizViewModel

class SelectDifficultyActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySelectDifficultyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySelectDifficultyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initBackClick()
        onClickIntents()
    }

    private fun initBackClick()
    {
        binding.difficultyBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun onClickIntents()
    {
        val category = intent.getStringExtra("category")!!

        binding.difficultyAny.setOnClickListener {
            val intent = Intent(this,QuizShowActivity::class.java)
            intent.putExtra("category",category)
            intent.putExtra("difficulty","")
            startActivity(intent)
        }

        binding.difficultyEasy.setOnClickListener {
            val intent = Intent(this,QuizShowActivity::class.java)
            intent.putExtra("category",category)
            intent.putExtra("difficulty","easy")
            startActivity(intent)
        }

        binding.difficultyMedium.setOnClickListener {
            val intent = Intent(this,QuizShowActivity::class.java)
            intent.putExtra("category",category)
            intent.putExtra("difficulty","medium")
            startActivity(intent)
        }

        binding.difficultyHard.setOnClickListener {
            val intent = Intent(this,QuizShowActivity::class.java)
            intent.putExtra("category",category)
            intent.putExtra("difficulty","hard")
            startActivity(intent)
        }

    }
}
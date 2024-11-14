package com.example.quizzapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizzapp.databinding.ActivityMainBinding
import com.example.quizzapp.view.activity.SelectDifficultyActivity
import com.example.quizzapp.viewModel.QuizViewModel

    class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        categoryIntent()
    }

    private fun categoryIntent()
    {
        binding.CategoryAny.setOnClickListener {
            val intent = Intent(this,SelectDifficultyActivity::class.java)
            intent.putExtra("category","")
            startActivity(intent)
        }

        binding.CategoryGK.setOnClickListener {
            val intent = Intent(this,SelectDifficultyActivity::class.java)
            intent.putExtra("category","9")
            startActivity(intent)
        }

        binding.CategorySport.setOnClickListener {
            val intent = Intent(this,SelectDifficultyActivity::class.java)
            intent.putExtra("category","21")
            startActivity(intent)
        }

        binding.CategoryHistory.setOnClickListener {
            val intent = Intent(this,SelectDifficultyActivity::class.java)
            intent.putExtra("category","23")
            startActivity(intent)
        }

        binding.CategoryMusic.setOnClickListener {
            val intent = Intent(this,SelectDifficultyActivity::class.java)
            intent.putExtra("category","12")
            startActivity(intent)
        }

        binding.CategoryScienceANature.setOnClickListener {
            val intent = Intent(this,SelectDifficultyActivity::class.java)
            intent.putExtra("category","17")
            startActivity(intent)
        }

        binding.CategoryAnimal.setOnClickListener {
            val intent = Intent(this,SelectDifficultyActivity::class.java)
            intent.putExtra("category","27")
            startActivity(intent)
        }

        binding.CategoryVehicles.setOnClickListener {
            val intent = Intent(this,SelectDifficultyActivity::class.java)
            intent.putExtra("category","28")
            startActivity(intent)
        }

        binding.CategoryMath.setOnClickListener {
            val intent = Intent(this,SelectDifficultyActivity::class.java)
            intent.putExtra("category","19")
            startActivity(intent)
        }

        binding.CategoryComputer.setOnClickListener {
            val intent = Intent(this,SelectDifficultyActivity::class.java)
            intent.putExtra("category","18")
            startActivity(intent)
        }

        binding.CategoryFilm.setOnClickListener {
            val intent = Intent(this,SelectDifficultyActivity::class.java)
            intent.putExtra("category","11")
            startActivity(intent)
        }

        binding.CategoryVideoGame.setOnClickListener {
            val intent = Intent(this,SelectDifficultyActivity::class.java)
            intent.putExtra("category","15")
            startActivity(intent)
        }
    }
}
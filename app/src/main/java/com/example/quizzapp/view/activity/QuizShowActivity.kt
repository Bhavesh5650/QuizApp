package com.example.quizzapp.view.activity

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizzapp.R
import com.example.quizzapp.databinding.ActivityQuizShowBinding
import com.example.quizzapp.viewModel.QuizViewModel

class QuizShowActivity : AppCompatActivity() {

    private lateinit var timer: CountDownTimer
    private lateinit var binding:ActivityQuizShowBinding
    private val viewModel by viewModels<QuizViewModel>()
    private lateinit var dialog:Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizShowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setCountDown()
        callApi()
        showDialog()
        observeData()
        selectOption()
        initNextClick()
    }

    private fun setCountDown()
    {
        timer = object : CountDownTimer(30000, 1000) {

            @SuppressLint("SetTextI18n", "ObjectAnimatorBinding")
            override fun onTick(millisUntilFinished: Long) {
                binding.progressView.progress = (millisUntilFinished / 1000).toInt();
                binding.setCountDownTxt.text = "${millisUntilFinished / 1000}"
            }
            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                viewModel.selectedOption = null;
                viewModel.changeQuiz()
                binding.setQuizCount.text = "${viewModel.count.value!!+1}"
                timer.cancel()
                timer.start()
            }
        }

    }

    private fun observeData()
    {
        viewModel.questionData.observe(this){
            binding.setQuizQuestion.text = it[0].question
            binding.optionOneText.text = it[0].optionList?.get(0)
            binding.optionTwoText.text = it[0].optionList?.get(1)
            binding.optionThreeText.text = it[0].optionList?.get(2)
            binding.optionFourText.text = it[0].optionList?.get(3)
            dialog.dismiss()
            timer.start()
        }

        viewModel.count.observe(this){
            binding.setQuizQuestion.text = viewModel.questionData.value?.get(it)?.question
            binding.optionOneText.text = viewModel.questionData.value?.get(it)?.optionList?.get(0)
            binding.optionTwoText.text = viewModel.questionData.value?.get(it)?.optionList?.get(1)
            binding.optionThreeText.text = viewModel.questionData.value?.get(it)?.optionList?.get(2)
            binding.optionFourText.text = viewModel.questionData.value?.get(it)?.optionList?.get(3)
        }
    }

    private fun selectOption()
    {
        binding.optionOneCard.setOnClickListener {
            binding.optionOneImage.visibility = View.VISIBLE
            binding.optionTwoImage.visibility = View.INVISIBLE
            binding.optionThreeImage.visibility = View.INVISIBLE
            binding.optionFourImage.visibility = View.INVISIBLE
            binding.nextQuizBtn.visibility = View.VISIBLE
            viewModel.selectedOption = viewModel.questionData.value?.get(viewModel.count.value!!)?.optionList?.get(0)
        }

        binding.optionTwoCard.setOnClickListener {
            binding.optionOneImage.visibility = View.INVISIBLE
            binding.optionTwoImage.visibility = View.VISIBLE
            binding.optionThreeImage.visibility = View.INVISIBLE
            binding.optionFourImage.visibility = View.INVISIBLE
            binding.nextQuizBtn.visibility = View.VISIBLE
            viewModel.selectedOption = viewModel.questionData.value?.get(viewModel.count.value!!)?.optionList?.get(1)
        }

        binding.optionThreeCard.setOnClickListener {
            binding.optionOneImage.visibility = View.INVISIBLE
            binding.optionTwoImage.visibility = View.INVISIBLE
            binding.optionThreeImage.visibility = View.VISIBLE
            binding.optionFourImage.visibility = View.INVISIBLE
            binding.nextQuizBtn.visibility = View.VISIBLE
            viewModel.selectedOption = viewModel.questionData.value?.get(viewModel.count.value!!)?.optionList?.get(2)
        }

        binding.optionFourCard.setOnClickListener {
            binding.optionOneImage.visibility = View.INVISIBLE
            binding.optionTwoImage.visibility = View.INVISIBLE
            binding.optionThreeImage.visibility = View.INVISIBLE
            binding.optionFourImage.visibility = View.VISIBLE
            binding.nextQuizBtn.visibility = View.VISIBLE
            viewModel.selectedOption = viewModel.questionData.value?.get(viewModel.count.value!!)?.optionList?.get(3)
        }

    }

    private fun showDialog()
    {
        dialog = Dialog(this)
        dialog.setContentView(R.layout.loader_sample_layout)
        dialog.setCancelable(false)
        dialog.show()
    }

    @SuppressLint("SetTextI18n")
    private fun callApi()
    {
        binding.setQuizCount.text = "${viewModel.count.value!!+1}"
        val category = intent.getStringExtra("category")
        val difficulty = intent.getStringExtra("difficulty")
        viewModel.category = category
        viewModel.difficulty = difficulty
        viewModel.getQuiz()
    }

    @SuppressLint("SetTextI18n")
    private fun initNextClick()
    {
        binding.nextQuizBtn.setOnClickListener {
            viewModel.changeQuiz()
            binding.setQuizCount.text = "${viewModel.count.value!!+1}"
            binding.optionOneImage.visibility = View.INVISIBLE
            binding.optionTwoImage.visibility = View.INVISIBLE
            binding.optionThreeImage.visibility = View.INVISIBLE
            binding.optionFourImage.visibility = View.INVISIBLE
            binding.nextQuizBtn.visibility = View.GONE
            timer.cancel()
            timer.start()
            if(viewModel.count.value == 9)
            {
                timer.cancel()
                timer.start()
                binding.nextQuizBtnText.text = "Submit"
                binding.nextQuizBtn.setOnClickListener {
                    viewModel.changeQuiz()
                    val intent = Intent(this@QuizShowActivity,QuizResultActivity::class.java)
                    intent.putExtra("correct",viewModel.correct)
                    intent.putExtra("incorrect",viewModel.incorrect)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}
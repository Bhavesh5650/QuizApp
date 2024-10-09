package com.example.quizzapp.viewModel

import android.service.autofill.UserData
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizzapp.data.model.QuestionModel
import com.example.quizzapp.data.model.QuizModel
import com.example.quizzapp.domain.DataRepository.Companion.repository
import kotlinx.coroutines.launch

class QuizViewModel : ViewModel() {

    private var questionMutableData = MutableLiveData<MutableList<QuestionModel>>()
    val questionData: LiveData<MutableList<QuestionModel>> = questionMutableData

    var category: String? = null
    var difficulty: String? = null
    private var countMutable = MutableLiveData(0)
    val count: LiveData<Int> = countMutable
    private var tempList = mutableListOf<QuestionModel>()
    var correct: Int = 0;
    var incorrect:Int =0
    var selectedOption: String? = null;

    fun getQuiz() {
        viewModelScope.launch {
            val quizList = repository.getQuiz(category = category!!, difficulty = difficulty!!)
            for (i in quizList?.results!!) {
                val optionList = i!!.incorrectAnswers
                optionList?.add(i.correctAnswer)
                optionList?.shuffle()
                tempList.add(QuestionModel(i.question!!, i.correctAnswer!!, optionList!!))
                questionMutableData.value = tempList
                Log.d(
                    "TAG", "getQuiz: ==================${
                        QuestionModel(
                            i.question!!, i.correctAnswer!!, optionList!!
                        )
                    }"
                )
            }
        }
    }

    fun changeQuiz() {
        if (countMutable.value!! < 9) {
            checkResult()
            Log.i("select Option", "checkResult: ===== $selectedOption ")
            Log.i("answer", "checkResult: ======= ${questionData.value!![count.value!!].answer}")
            countMutable.value = countMutable.value!! + 1
        }else{
            checkResult()
            Log.i("select Option", "checkResult: ===== $selectedOption ")
            Log.i("answer", "checkResult: ======= ${questionData.value!![count.value!!].answer}")
            Log.d("Points", "changeQuiz: Correct is $correct")
            Log.d("Points", "changeQuiz: Incorrect is $incorrect")
        }
    }
    fun checkResult() {
        if (questionData.value!![count.value!!].answer == selectedOption) {
            correct++;
        }
        else
        {
            incorrect++
        }
    }


}
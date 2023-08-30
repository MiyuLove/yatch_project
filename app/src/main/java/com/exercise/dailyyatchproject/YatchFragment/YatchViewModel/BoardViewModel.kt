package com.exercise.dailyyatchproject.YatchFragment.YatchViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exercise.dailyyatchproject.YatchFragment.RecyclerAdapters.MainUserData

class BoardViewModel : ViewModel() {
    private var gameMode = 0
    private var userTurn = 0
    private var userTurnMaximum = 0
    private var userDataList = listOf<MainUserData>()

    private val scoreListMaxSize = 13

    private var _scoreList = MutableLiveData(List(scoreListMaxSize) { 0 })
    val scoreList : LiveData<List<Int>>
        get() = _scoreList

    private var _clickedList = MutableLiveData(List(userDataList.size) { false })
    val clickedList : LiveData<List<Boolean>>
        get() = _clickedList

    private var _score = MutableLiveData(0)
    val score : LiveData<Int>
        get() = _score

    private var _bonusScore = MutableLiveData(0)
    val bonusScore : LiveData<Int>
        get() = _bonusScore

    private var _currentName = MutableLiveData("")
    val currentName : LiveData<String>
        get() = _currentName

    private var userScoreData = mutableListOf<MutableList<Int>>()

    fun getUserData() = userDataList
    fun setUserData(mainUserDataList : List<MainUserData>){
        userDataList = mainUserDataList
        userTurnMaximum = mainUserDataList.size
        repeat(userDataList.size) {
            userScoreData.add(MutableList(scoreListMaxSize) { 0 })
        }
        setClickedData()
        setCurrentName()
    }

    private fun setClickedData(){
        val currentList = MutableList(userTurnMaximum){false}
        currentList[userTurn] = true
        _clickedList.value = currentList
    }

    private fun setCurrentName(){
        _currentName.value = userDataList[userTurn].nickname
    }

    fun changeScoreList(index : Int, value : Int){
        val currentList = scoreList.value?.toMutableList() ?: mutableListOf()
        currentList[index] = value
        changeScore(currentList)
        _scoreList.postValue(currentList)
    }

    private fun changeScore(currentList : List<Int>){
        var leftSum = 0
        var rightSum = 0
        for(i in 0 until 6) leftSum += currentList[i]
        _bonusScore.value = leftSum
        if(leftSum >= 63) leftSum+=35

        for(i in 6 until currentList.size) rightSum += currentList[i]

        _score.value = leftSum + rightSum
        userDataList[userTurn].score = score.value!!
    }

    fun beforeTurn(){
        saveUserScore()
        userTurn--

        if(userTurn < 0)
            userTurn = userTurnMaximum - 1

        changeUser()
    }

    fun changeTurn(){
        saveUserScore()
        userTurn++

        if(userTurn >= userTurnMaximum)
            userTurn = 0

        changeUser()
    }

    fun changeTurn(turn : Int){
        saveUserScore()
        userTurn = turn
        changeUser()
    }

    private fun saveUserScore(){
        userScoreData[userTurn] = _scoreList.value!!.toMutableList()
    }


    private fun changeUser(){
        _scoreList.value = userScoreData[userTurn]
        changeScore(userScoreData[userTurn])
        setClickedData()
        setCurrentName()
    }
}
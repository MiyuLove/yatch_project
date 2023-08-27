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
        var sum = 0
        for(i in currentList) sum += i
        checkBonus(currentList)

        _score.value = sum + _bonusScore.value!!
        userDataList[userTurn].score = score.value!!
    }

    private fun checkBonus(currentList: List<Int>){
        var sum = 0

        for(i in 0..5){
            sum += currentList[i]
        }
        if(sum >= 63)
            _bonusScore.value = 35
        else
            _bonusScore.value = 0
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
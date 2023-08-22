package com.exercise.dailyyatchproject.YatchFragment.YatchViewModel

import android.service.autofill.UserData
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BoardViewModel : ViewModel() {
    private var gameMode = 0

    private var buttonTextList = MutableLiveData<List<Int>>(List(14) { 0 })
    val textList : LiveData<List<Int>>
        get() = buttonTextList

    private var _score = MutableLiveData(0)
    val score : LiveData<Int>
        get() = _score

    init{

    }

    fun changeButtonTextList(index : Int, value : Int){
        val currentList = buttonTextList.value?.toMutableList() ?: mutableListOf()
        currentList[index] = value
        changeScore(currentList)
        buttonTextList.postValue(currentList)
    }

    private fun changeScore(currentList : MutableList<Int>){
        var sum = 0
        for(i in currentList) sum += i

        _score.value = sum
    }
}
package com.exercise.dailyyatchproject.YatchFragment.YatchViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class DiceViewModel() : ViewModel() {
    private var playerTurn = MutableLiveData(0)
    val userTurn : LiveData<Int>
        get() = playerTurn
    var trueTurn = 0

    private var userTurnMax = 0

    private var _scoreList = MutableLiveData(List(14) { 0 })
    val scoreList : LiveData<List<Int>>
        get() = _scoreList

    private var _diceList = MutableLiveData(List(5) { 0 })
    val diceList : LiveData<List<Int>>
        get() = _diceList

    var resultList = mutableListOf<List<Int>>()

    private var _selectList = MutableLiveData(List(5) { 0 })
    val selectList : LiveData<List<Int>>
        get() = _selectList


    fun castingDice(){
        val tmpDiceList = _diceList.value!!.toMutableList()
        val randInt = Random
        for(i in tmpDiceList.indices){
            if(tmpDiceList[i] != 0)
                tmpDiceList[i] = randInt.nextInt(1,7)
        }
        _diceList.value = tmpDiceList
    }

    fun clickDice(diceNumber : Int){
        val tmpDiceList = _diceList.value!!.toMutableList()
        val tmpSelectList = _selectList.value!!.toMutableList()

        if(tmpDiceList[diceNumber] == 0)
            return

        tmpSelectList[diceNumber] = tmpDiceList[diceNumber]
        tmpDiceList[diceNumber] = 0

        _diceList.value = tmpDiceList
        _selectList.value = tmpSelectList
    }

    fun clickSelectedDice(diceNumber: Int){
        val tmpSelectList = _selectList.value!!.toMutableList()
        val tmpDiceList = _diceList.value!!.toMutableList()

        if(tmpSelectList[diceNumber] == 0)
            return

        tmpDiceList[diceNumber] = tmpSelectList[diceNumber]
        tmpSelectList[diceNumber] = 0

        _selectList.value = tmpSelectList
        _diceList.value = tmpDiceList
    }

    fun initDiceList(){
        _diceList.value = listOf(-1,-1,-1,-1,-1,)
    }

    fun initSelecttList(){
        _selectList.value = listOf(0,0,0,0,0)
    }

    private lateinit var userPlayDataList : MutableList<UserPlayData>

    fun castDice(diceNumber : Int){

    }
    //인원 명단과 수 확인
    fun setUserPlayDataList(userDataList: List<String>){
        val userPlayDataMutableList = mutableListOf<UserPlayData>()
        userTurnMax = userDataList.size
    }

    fun userPlus(){
        playerTurn.value = userTurn.value?.plus(10)
        Log.d("board ", playerTurn.value.toString())
    }

    fun setTurn(){

    }

    private data class UserPlayData(
        val resultList : List<List<Int>>,
        val selectedList : List<List<Int>>,
        val selectedBoard : Int,
    )

}
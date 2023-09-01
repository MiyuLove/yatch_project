package com.exercise.dailyyatchproject.YatchFragment.YatchViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random
import kotlin.text.Typography.tm

class DiceViewModel() : ViewModel() {
    //-1은 초기상태라 변경해야만
    private var _diceList = MutableLiveData(List(5) { -1 })
    val diceList : LiveData<List<Int>>
        get() = _diceList

    private var _selectList = MutableLiveData(List(5) { 0 })
    val selectList : LiveData<List<Int>>
        get() = _selectList

    private var _castCount = MutableLiveData(0)
    val castCount : LiveData<Int>
        get() = _castCount

    fun setCastCount(count : Int){
        _castCount.value = count
    }

    fun setCastCount() : Boolean{
        _castCount.value = _castCount.value!! - 1
        return castCount.value!! > 0
    }

    fun castingDice(){
        val tmpDiceList = _diceList.value!!.toMutableList()
        val randInt = Random

        //-1은 선택 받아서 변경할 수 없는 상태
        for(i in tmpDiceList.indices){
            if(tmpDiceList[i] != 0)
                tmpDiceList[i] = randInt.nextInt(1,7)
        }
        _diceList.value = tmpDiceList
    }

    fun clickDice(diceNumber : Int){
        val newDiceList = _diceList.value!!.toMutableList()
        val newSelectList = _selectList.value!!.toMutableList()

        if(newDiceList[diceNumber] == -1) return

        var tmpValue = newDiceList[diceNumber]
        newDiceList[diceNumber] = newSelectList[diceNumber]
        newSelectList[diceNumber] = tmpValue
        _diceList.value = newDiceList
        _selectList.value = newSelectList
        Log.d("viewModel", newSelectList.toString())
        Log.d("viewModel", newDiceList.toString())

        return
    }

    fun getDiceList() : List<Int>{
        val returnList = mutableListOf(0,0,0,0,0)
        for(i in _diceList.value!!.indices){
            if(_diceList.value!![i] == -1)
                return returnList

            returnList[i] = if(_diceList.value!![i] != 0) _diceList.value!![i] else _selectList.value!![i]
        }
        return returnList
    }
}
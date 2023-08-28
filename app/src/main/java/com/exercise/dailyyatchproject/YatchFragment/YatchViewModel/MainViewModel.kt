package com.exercise.dailyyatchproject.YatchFragment.YatchViewModel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import com.exercise.dailyyatchproject.LocalDatabase.Entity.ResultEntity
import com.exercise.dailyyatchproject.LocalDatabase.Entity.UserEntity
import com.exercise.dailyyatchproject.YatchFragment.RecyclerAdapters.MainUserData

class MainViewModel : ViewModel() {
    private var gameMode = 1
    private lateinit var userList : List<MainUserData>
    private lateinit var diceViewModel: DiceViewModel
    private var resultEntity: ResultEntity? = null

    fun getResultEntityList() = resultEntity
    fun setResultEntityList(resultEntity: ResultEntity?){
        this.resultEntity = resultEntity
    }
    fun getDiceViewModel() = diceViewModel
    fun setDiceViewModel(diceViewModel: DiceViewModel){
        this.diceViewModel = diceViewModel
    }

    fun setGameMode(mode : Int){
        gameMode = mode
    }

    fun getGameMode() = gameMode
    fun getMainUserData() = userList
//    fun setMainUserData(mainUserData: List<MainUserData>){
//        userList = mainUserData
//    }
    fun setMainUserData(liveUserList : List<UserEntity>){
        val _userList = mutableListOf<MainUserData>()
        liveUserList.forEach{
            _userList.add(MainUserData(it.text, it.profilePhoto))
        }
        userList = _userList
    }

    fun initiateScore(){
        val _userList = userList.toMutableList()
        _userList.forEach {
            it.score = 0
        }
        userList = _userList
    }

    fun resultData(userList : List<MainUserData>){
        this.userList = userList
    }
}
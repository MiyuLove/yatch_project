package com.exercise.dailyyatchproject.YatchFragment.YatchViewModel

import android.graphics.Bitmap
import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.exercise.dailyyatchproject.LocalDatabase.Entity.ResultEntity
import com.exercise.dailyyatchproject.LocalDatabase.Repository.ResultRepository
import com.exercise.dailyyatchproject.YatchFragment.RecyclerAdapters.MainUserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Date

class HistoryViewModel : ViewModel() {
    private val repository = ResultRepository()
    lateinit var resultEntityList : LiveData<List<ResultEntity>>
    private lateinit var userList : List<MainUserData>

    fun getUserList() = userList

    fun setUserSortedList(mainUserList : MutableList<MainUserData>){
        userList = mainUserList.sortedByDescending { it.score }
        Log.d("result", userList.toString())
    }

    fun getResultList(index : Int) : ResultEntity? {
        return resultEntityList.value?.get(index)
    }

    fun storeUserListToResultEntity(){
        create(ResultEntity(0,
            getUserNicknameList(),
            getUserScoreList(),
            Date()))
    }

    private fun getUserNicknameList(): List<String>{
        val userNicknameList = mutableListOf<String>()
        userList.forEach {
            userNicknameList.add(it.nickname)
        }
        return userNicknameList
    }

    private fun getUserScoreList(): List<Int>{
        val userScoreList = mutableListOf<Int>()
        userList.forEach {
            userScoreList.add(it.score)
        }
        return userScoreList
    }

    fun create(resultEntity: ResultEntity) = viewModelScope.launch (Dispatchers.IO){
        repository.create(resultEntity)
    }

    fun read(){
        resultEntityList = repository.read().asLiveData()
    }

    fun reverseRead() = viewModelScope.launch(Dispatchers.IO){
        val originalList = resultEntityList.value
        val reverseList = originalList!!.reversed()
        //이건 나중에 interface 작업 끝나면 합시다 ^6^
    }

    fun update(){

    }

    fun delete(){

    }

    fun deleteAllData() = viewModelScope.launch (Dispatchers.IO){
        repository.deleteAllData()
    }
}
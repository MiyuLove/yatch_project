package com.exercise.dailyyatchproject.YatchFragment.YatchViewModel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercise.dailyyatchproject.LocalDatabase.Entity.ResultEntity
import com.exercise.dailyyatchproject.LocalDatabase.Repository.ResultRepository
import com.exercise.dailyyatchproject.YatchFragment.RecyclerAdapters.MainUserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataViewModel : ViewModel (){
    private val repository = ResultRepository()

    private val resultLiveData = MutableLiveData<ResultEntity>()
    val resultEntity: LiveData<ResultEntity>
        get() = resultLiveData

    private lateinit var deleteResultEntity : ResultEntity

    fun setResultEntity(resultEntity: ResultEntity){
        resultLiveData.value = resultEntity
        deleteResultEntity = resultEntity
    }

    fun resultEntityToMainUserDataList(resultEntity: ResultEntity) : List<MainUserData>{
        //string, bitmap, score
        val userList = mutableListOf<MainUserData>()
        val userNicknameList = mutableListOf<String>()
        val userProfileList = mutableListOf<Bitmap>()
        val userScoreList = mutableListOf<Int>()
        resultEntity.userList.forEach {
            userNicknameList.add(it)
        }

        resultEntity.scoreList.forEach {
            userScoreList.add(it)
        }

        for(i in userNicknameList.indices){
            userList.add(MainUserData(userNicknameList[i],null,userScoreList[i]))
        }

        return userList
    }

    fun deleteResult() = viewModelScope.launch(Dispatchers.IO){
        Log.d("result", deleteResultEntity.toString())
        repository.delete(deleteResultEntity)
    }
}
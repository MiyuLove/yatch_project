package com.exercise.dailyyatchproject.YatchFragment.YatchViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.exercise.dailyyatchproject.LocalDatabase.Entity.ResultEntity
import com.exercise.dailyyatchproject.LocalDatabase.Repository.ResultRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel : ViewModel() {
    private val repository = ResultRepository()
    lateinit var resultEntityList : LiveData<List<ResultEntity>>

    fun create(resultEntity: ResultEntity) = viewModelScope.launch (Dispatchers.IO){
        repository.create(resultEntity)
    }

    fun read(){
        resultEntityList = repository.read().asLiveData()
    }

    fun update(){

    }

    fun delete(){

    }

    fun deleteAllData() = viewModelScope.launch (Dispatchers.IO){
        repository.deleteAllData()
    }
}
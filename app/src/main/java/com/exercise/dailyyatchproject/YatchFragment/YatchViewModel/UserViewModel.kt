package com.exercise.dailyyatchproject.YatchFragment.YatchViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.exercise.dailyyatchproject.LocalDatabase.Entity.UserEntity
import com.exercise.dailyyatchproject.LocalDatabase.Repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class UserViewModel : ViewModel(){
    private val repository = UserRepository()
    lateinit var userEntityList : LiveData<List<UserEntity>>

    fun create(userEntity: UserEntity) = viewModelScope.launch (Dispatchers.IO){
        repository.create(userEntity)
    }

    fun read(){

        viewModelScope.launch(Dispatchers.IO) {
            userEntityList = repository.read().asLiveData()

        }

    }

    fun delete(userEntity: UserEntity) =viewModelScope.launch (Dispatchers.IO) {
        repository.delete(userEntity)
    }

    fun deleteAllData() = viewModelScope.launch (Dispatchers.IO){
        repository.deleteAllData()
    }
}
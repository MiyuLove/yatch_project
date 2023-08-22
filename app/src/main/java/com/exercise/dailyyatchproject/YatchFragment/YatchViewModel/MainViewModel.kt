package com.exercise.dailyyatchproject.YatchFragment.YatchViewModel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import com.exercise.dailyyatchproject.LocalDatabase.Entity.UserEntity

class MainViewModel : ViewModel() {
    private var gameMode = 0
    private var userList = mutableListOf<MainUserData>()

    fun setGameMode(mode : Int){
        gameMode = mode
    }

    fun setMainUserData(liveUserList : List<UserEntity>){
        liveUserList.forEach{
            userList.add(MainUserData(it.text, it.profilePhoto))
            Log.d("user", it.text)
        }
    }

    data class MainUserData(
        val nickname: String,
        val profilePhoto: Bitmap?,
        val score: Int = 0
    )
}
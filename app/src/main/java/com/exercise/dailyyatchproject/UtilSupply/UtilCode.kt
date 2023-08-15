package com.exercise.dailyyatchproject.UtilSupply

import android.app.Activity
import androidx.core.app.ActivityCompat

class UtilCode {
    companion object{
        val ucu = UtilCode()
    }

    fun logChat(s : String){
        println("dd123 : " + s)
    }

    fun logChat(s : Int){
        logChat(s.toString())
    }

    fun logChat(s : Any){
        logChat(s.toString())
    }

    fun activityPerfectExit(activity : Activity){
        ActivityCompat.finishAffinity(activity)
        System.exit(0)
    }
}
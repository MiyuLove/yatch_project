package com.exercise.dailyyatchproject.UtilSupply

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.widget.Toast
import androidx.core.app.ActivityCompat

class UtilCode {
    private var toast : Toast? = null

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

    fun makeToast(context : Context, text : String){
        try {
            toast?.cancel()
            toast = Toast.makeText(context,text, Toast.LENGTH_SHORT)
            toast?.show()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}
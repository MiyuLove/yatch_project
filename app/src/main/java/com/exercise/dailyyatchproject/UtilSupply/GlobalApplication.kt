package com.exercise.dailyyatchproject.UtilSupply

import android.app.Application
import android.content.Context
import android.util.DisplayMetrics

class GlobalApplication : Application() {
    init{
        instance = this
    }
    companion object{

        private var instance : GlobalApplication? = null

        fun context() : Context{
            return instance!!.applicationContext
        }

        private var deviceWidth = 0
        private var deviceHeight = 0

        fun getDeviceWidth() = deviceWidth
        fun getDeviceHeight() = deviceHeight
    }

    private fun setDevice() {
        val displayMetrics = applicationContext.resources.displayMetrics
        deviceWidth = displayMetrics.widthPixels
        deviceHeight = displayMetrics.heightPixels
    }

    override fun onCreate() {
        super.onCreate()
        setDevice()
    }
}
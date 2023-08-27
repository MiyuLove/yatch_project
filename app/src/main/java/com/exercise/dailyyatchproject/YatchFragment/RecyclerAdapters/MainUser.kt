package com.exercise.dailyyatchproject.YatchFragment.RecyclerAdapters

import android.graphics.Bitmap


data class MainUserData(
    val nickname: String,
    val profilePhoto: Bitmap?,
    var score: Int = 0
)
package com.exercise.dailyyatchproject.YatchFragment.Board

import android.util.Log

private const val debugString = "Board_Base"
open class BoardBase (width : Int, height : Int){
    val widthMidLine = width/2
    val heightMidLine = height/2

    val leftLayoutYList : List<Int>
    val rightLayoutYList : List<Int>


    init {
        Log.d(debugString, width.toString())
        Log.d(debugString, height.toString())
        Log.d(debugString, widthMidLine.toString())
        Log.d(debugString, heightMidLine.toString())

        leftLayoutYList = makeLayoutYList(7,height)
        rightLayoutYList = makeLayoutYList(7,height)

        //horizontal
        resizeSize(width,height)
    }

    private fun makeLayoutYList(num : Int, height: Int): List<Int> {
        val list = mutableListOf<Int>()
        var _translationY = 0
        for(i in 0 until num) {
            list.add(_translationY)
            _translationY += height / num
        }
        return list
    }

    private fun resizeSize(width: Int, height: Int){

    }
}
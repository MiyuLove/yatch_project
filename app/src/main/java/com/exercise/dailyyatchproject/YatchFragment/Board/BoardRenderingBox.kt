package com.exercise.dailyyatchproject.YatchFragment.Board

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.exercise.dailyyatchproject.UtilSupply.UtilCode
import com.exercise.dailyyatchproject.UtilSupply.UtilCode.Companion.ucu
import com.exercise.dailyyatchproject.databinding.BoardButtonBoxBinding

class BoardRenderingBox(width :Int, height : Int, inflater: LayoutInflater, container: ViewGroup?){
    private var boardButtonList : List<View>
    private var boardBase : BoardBase

    private var boardListNumber = 14
    val boardTextList = mutableListOf<BoardButtonBoxBinding>()

    init{
        this.boardBase = BoardBase(width,height)
        boardButtonList = makeBoardButtonList(inflater, container)
    }
    fun getBoardButtonList() = boardButtonList.toList()

    private fun makeBoardButtonList(inflater: LayoutInflater, container: ViewGroup?) : List<View>{
        val buttonList = makeBoardButton(inflater,container)
        setBoardButtonTranslationXY(buttonList)
        return buttonList
    }

    private fun makeBoardButton(inflater: LayoutInflater, container: ViewGroup?) : List<View>{
        var buttonList = mutableListOf<View>()
        for (i in 0 until boardListNumber) {
            val boardButtonBinding = BoardButtonBoxBinding.inflate(inflater, container, false)
            buttonList.add(boardButtonBinding.boardButton)
            boardTextList.add(boardButtonBinding)
        }
        return buttonList
    }

    private fun setBoardButtonTranslationXY(buttonList :List <View>){
        for (i in boardListNumber/2 until boardListNumber){
            buttonList[i].translationX = boardBase.widthMidLine.toFloat()
        }
        for (i in 0 until boardListNumber/2){
            buttonList[i].translationY = boardBase.leftLayoutYList[i].toFloat()
            buttonList[i+boardListNumber/2].translationY = boardBase.rightLayoutYList[i].toFloat()
        }
    }

    fun setBoardButtonOnClick(clickEvent : () -> Unit){
        for (i in boardButtonList){
            i.setOnClickListener {
                clickEvent()
            }
        }
    }
}
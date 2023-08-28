package com.exercise.dailyyatchproject.YatchFragment.DialogPackage

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import com.exercise.dailyyatchproject.databinding.DiceBoardDialogBinding

class DiceBoardDialog(private val context : Context,
                      private val selectedDice : List<Int>,
                      private val castedDice : List<Int>)
{
    val binding : DiceBoardDialogBinding = DiceBoardDialogBinding.inflate(LayoutInflater.from(context))
    init {
        initDialog()
        initView()
    }

    fun showDiceBoardDialog(){
        Log.d("Dice dialog", selectedDice.toString())
        Log.d("Dice dialog", castedDice.toString())
    }

    private fun initDialog(){
    }

    private fun initView(){
        initSelectedDiceImage()
        initCastedDiceImage()
        initButton()
    }

    private fun initButton(){
        binding.diceDialogButton.setOnClickListener {

        }
    }

    private fun initSelectedDiceImage() = with(binding){
        val selectedDiceImage = listOf(
            diceBoardDialogSelectedDice1,
            diceBoardDialogSelectedDice2,
            diceBoardDialogSelectedDice3,
            diceBoardDialogSelectedDice4,
            diceBoardDialogSelectedDice5
        )

        for(i in selectedDiceImage.indices){
            selectedDiceImage[i].setOnClickListener {
                Log.d("Dice dialog", selectedDice[i].toString())
            }
        }
    }
    private fun initCastedDiceImage() = with(binding){
        val castedDiceImage = listOf(
            diceBoardDialogDice1,
            diceBoardDialogDice2,
            diceBoardDialogDice3,
            diceBoardDialogDice4,
            diceBoardDialogDice5,
        )

        for(i in castedDiceImage.indices){
            castedDiceImage[i].setOnClickListener {
                Log.d("Dice dialog", castedDice[i].toString())
            }
        }
    }
}
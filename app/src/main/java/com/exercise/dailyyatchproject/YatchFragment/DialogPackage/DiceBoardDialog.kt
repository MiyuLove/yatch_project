package com.exercise.dailyyatchproject.YatchFragment.DialogPackage

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.exercise.dailyyatchproject.R
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.DiceViewModel
import com.exercise.dailyyatchproject.databinding.DiceBoardDialogBinding

class DiceBoardDialog(val context : Context)
{
    private val viewModel : DiceViewModel = DiceViewModel()
    //private val viewModel : DiceViewModel = ViewModelProvider((context as AppCompatActivity))[DiceViewModel::class.java]
    private val binding : DiceBoardDialogBinding = DiceBoardDialogBinding.inflate(LayoutInflater.from(context))
    private val dialog = Dialog(context)

    init {
        initView()
        initDialog()
        dialog.setContentView(binding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }


    fun showDiceBoardDialog(){
        dialog.show()
    }

    private fun initDialog(){
        dialog.setCancelable(false)
        dialog.setContentView(binding.root)
    }

    private fun initView(){
        linkViewModelLiveData()
        initButton()
    }

    private fun linkViewModelLiveData(){
        val imageList = listOf(
            R.drawable.dice_num1,
            R.drawable.dice_num2,
            R.drawable.dice_num3,
            R.drawable.dice_num4,
            R.drawable.dice_num5,
            R.drawable.dice_num6,
        )
        setSelectedDiceImage(imageList)
        setCastedDiceImage(imageList)
    }

    private fun initButton() = with(binding){
        diceDialogButton.setOnClickListener {
            viewModel.castingDice()
        }

        diceDialogScoreButton.setOnClickListener {
            dialog.dismiss()
        }

        val clickedButtonList = listOf(
            diceBoardDialogSelectedDice1,
            diceBoardDialogSelectedDice2,
            diceBoardDialogSelectedDice3,
            diceBoardDialogSelectedDice4,
            diceBoardDialogSelectedDice5
        )

        val castedDiceImage = listOf(
            diceBoardDialogDice1,
            diceBoardDialogDice2,
            diceBoardDialogDice3,
            diceBoardDialogDice4,
            diceBoardDialogDice5
        )

        for(i in clickedButtonList.indices){
            clickedButtonList[i].setOnClickListener {
                viewModel.clickDice(i)
            }
        }

        for(i in castedDiceImage.indices){
            castedDiceImage[i].setOnClickListener {
                viewModel.clickDice(i)
            }
        }
    }

    private fun setSelectedDiceImage(imageList : List<Int>) = with(binding){
        val selectedDiceImage = listOf(
            diceBoardDialogSelectedDice1,
            diceBoardDialogSelectedDice2,
            diceBoardDialogSelectedDice3,
            diceBoardDialogSelectedDice4,
            diceBoardDialogSelectedDice5
        )

        viewModel.selectList.observe(context as AppCompatActivity){selectList ->
            var selectIndex = 0
            selectList.forEach {
                if(it == 0)
                    selectedDiceImage[selectIndex].setImageResource(R.drawable.ic_launcher_foreground)
                else
                    selectedDiceImage[selectIndex].setImageResource(imageList[it-1])

                selectIndex++
            }
        }
    }

    private fun setCastedDiceImage(imageList : List<Int>) = with(binding){
        val castedDiceImage = listOf(
            diceBoardDialogDice1,
            diceBoardDialogDice2,
            diceBoardDialogDice3,
            diceBoardDialogDice4,
            diceBoardDialogDice5
        )

        viewModel.diceList.observe(context as AppCompatActivity){diceList ->
            var buttonIndex = 0
            diceList.forEach {
                if(it == 0)
                    castedDiceImage[buttonIndex].setImageResource(R.drawable.ic_launcher_foreground)
                else {
                    var index = if(it == -1) 0 else it-1
                    castedDiceImage[buttonIndex].setImageResource(imageList[index])
                }

                buttonIndex++
                //castedDiceImage[buttonIndex++].setImageResource(imageList[if(it == -1) 1 else it])
            }
        }
    }
}
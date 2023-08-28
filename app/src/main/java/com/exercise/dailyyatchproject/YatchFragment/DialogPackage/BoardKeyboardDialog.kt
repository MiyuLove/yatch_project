package com.exercise.dailyyatchproject.YatchFragment.DialogPackage

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.GridLayout
import androidx.core.view.marginLeft
import com.exercise.dailyyatchproject.R
import com.exercise.dailyyatchproject.YatchFragment.OnBoardCallback
import com.exercise.dailyyatchproject.databinding.KeyboardDialogBinding

class BoardKeyboardDialog(private val context : Context,
private val listener: OnBoardCallback) {

    private val binding : KeyboardDialogBinding
    private val dialog = Dialog(context)
    private lateinit var onUserListCallback : OnBoardCallback
    private var content = 0
    private var score = 0

    init{
        binding = KeyboardDialogBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)
        makeDialogButton()
    }

    fun setDialogDismissListener(listener: OnBoardCallback){
        onUserListCallback = listener
    }

    fun show(content : Int){
        binding.dialogTitle.text = content.toString()

        showSettingDialog()
        this.content = content

        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    private fun showSettingDialog() = with(binding){
        dialogTitle.text = "점수를 입력하세요."
        dialogScore.text = "0점"
        score = 0
    }

    private fun makeDialogButton(){
        for(i in 0 until 4) {
            for (j in 0 until 3) {
                val index = i * 3 + j + 1
                val button = Button(binding.root.context)
                val params = GridLayout.LayoutParams().apply {
                    rowSpec = GridLayout.spec(i, 1f)
                    columnSpec = GridLayout.spec(j, 1f)
                    width = 0
                    height = 0
                    setMargins(10,10,10,10)
                }
                button.layoutParams = params

                button.setOnClickListener {
                    setScoreText(index)
                }

                button.setBackgroundResource(R.color.custom_keyboard_color)
                button.setTextColor(context.getColor(R.color.custom_keyboard_text_color))

                button.text = (index).toString()
                if(index == 10){
                    button.text = "<-"
                }

                if(index == 11){
                    button.text = "0"
                }

                if(index == 12){
                    button.text = "OK"
                }

                binding.dialogBoardLayout.addView(button)
            }
        }
    }
    private fun setScoreText(_number : Int){
        Log.d("setScoreText", _number.toString())
        if(score == 0 && _number == 0) return

        if(_number == 10){
            score /= 10
            binding.dialogScore.text = score.toString()
            return
        }

        if(_number == 12){
            listener.onDialogDismissed(this.content,score)
            dialog.dismiss()
            return
        }

        if(score > 150) return

        val number = if(_number < 10) _number else 0

        score = score * 10 + number
        binding.dialogScore.text = "$score 점"
    }
}
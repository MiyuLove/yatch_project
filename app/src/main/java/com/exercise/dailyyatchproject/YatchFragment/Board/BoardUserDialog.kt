package com.exercise.dailyyatchproject.YatchFragment.Board

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import com.exercise.dailyyatchproject.YatchFragment.OnDialogDismissListener
import com.exercise.dailyyatchproject.YatchFragment.OnUserListCallback
import com.exercise.dailyyatchproject.databinding.KeyboardDialogBinding
import com.exercise.dailyyatchproject.databinding.UserInputDialogBinding

class BoardUserDialog (val context : Context,
private val onUserListCallback: OnUserListCallback){

    private val binding : UserInputDialogBinding
    private val dialog = Dialog(context)

    init{
        binding = UserInputDialogBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)
        dialog.setCanceledOnTouchOutside(false)
        initView()
    }

    private fun initView(){
        binding.userInputCancelButton.setOnClickListener {
            dialog.dismiss()
        }

        binding.userInputOkButton.setOnClickListener {
            onUserListCallback.onUserListAdded(binding.userInputNickname.text.toString())
            dialog.dismiss()
        }
    }

    fun show(){
        dialog.show()
    }

}
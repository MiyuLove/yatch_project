package com.exercise.dailyyatchproject.YatchFragment.Board

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import com.exercise.dailyyatchproject.YatchFragment.OnUserListCallback
import com.exercise.dailyyatchproject.databinding.UserInputDialogBinding

class BoardUserDialog (val context : Context,
private val onUserListCallback: OnUserListCallback){

    private val binding : UserInputDialogBinding
    private val dialog = Dialog(context)

    init{
        binding = UserInputDialogBinding.inflate(LayoutInflater.from(context))
        initDialog()
        initView()
    }

    private fun initDialog() = with(dialog){
        setContentView(binding.root)
        setCanceledOnTouchOutside(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun initView() = with(binding){

        userInputCancelButton.setOnClickListener {
            onUserListCallback.onUserAddedState(false)
            dialog.dismiss()
        }

        userInputOkButton.setOnClickListener {
            if(userInputNickname.text.isEmpty()) {
                userInputNickname.hint = "이름을 입력해!"
            }
            else{
                val modifiedText =userInputNickname.text.toString().replace(" ","").replace("\n","")
                onUserListCallback.onUserListAdded(modifiedText)
                onUserListCallback.onUserAddedState(false)
                dialog.dismiss()
            }
        }
    }

    fun show(){
        dialog.show()
    }

}
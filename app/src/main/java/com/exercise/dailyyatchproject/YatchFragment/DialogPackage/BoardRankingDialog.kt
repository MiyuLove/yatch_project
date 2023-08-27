package com.exercise.dailyyatchproject.YatchFragment.DialogPackage

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.dailyyatchproject.YatchFragment.RecyclerAdapters.BoardRankingRecyclerAdapter
import com.exercise.dailyyatchproject.YatchFragment.RecyclerAdapters.MainUserData
import com.exercise.dailyyatchproject.databinding.BoardRankingDialogBinding

class BoardRankingDialog(private val context : Context, private val userData: List<MainUserData>) {
    private val binding : BoardRankingDialogBinding
    private val dialog = Dialog(context)

    init {
        binding = BoardRankingDialogBinding.inflate(LayoutInflater.from(context))

        dialog.setContentView(binding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun show(){
        binding.boardRankingLayout.layoutManager = LinearLayoutManager(context)
        binding.boardRankingLayout.adapter = BoardRankingRecyclerAdapter(userData)
        dialog.show()
    }
}
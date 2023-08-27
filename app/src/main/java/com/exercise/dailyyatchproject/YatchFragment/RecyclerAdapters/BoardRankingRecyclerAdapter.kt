package com.exercise.dailyyatchproject.YatchFragment.RecyclerAdapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exercise.dailyyatchproject.databinding.BoardUserItemBinding

class BoardRankingRecyclerAdapter (private val userList : List<MainUserData>):
    RecyclerView.Adapter<BoardRankingRecyclerAdapter.ViewHolder>() {

    class ViewHolder(binding: BoardUserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val nickname = binding.boardDialogNickname
        val pImage = binding.boardDialogImage
        val score = binding.boardDialogScore
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = BoardUserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val scoreText="Score : ${userList[position].score}"
        holder.nickname.text = userList[position].nickname
        holder.pImage.setImageBitmap(userList[position].profilePhoto)
        holder.score.text = scoreText
    }

    override fun getItemCount(): Int {
        Log.d("user view", userList.size.toString())
        return userList.size
    }
}
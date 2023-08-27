package com.exercise.dailyyatchproject.YatchFragment.RecyclerAdapters

import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.exercise.dailyyatchproject.R
import com.exercise.dailyyatchproject.YatchFragment.OnBoardCallback
import com.exercise.dailyyatchproject.YatchFragment.OnUserListCallback
import com.exercise.dailyyatchproject.databinding.YatchBoardUserItemBinding

class BoardRecyclerAdapter(private val userClicked : List<Boolean>, private val userList : List<MainUserData>,
     private val onBoardCallback: OnBoardCallback)
    : RecyclerView.Adapter<BoardRecyclerAdapter.ViewHolder>() {

    class ViewHolder(binding : YatchBoardUserItemBinding) : RecyclerView.ViewHolder(binding.root){
        val userProfileId = binding.yatchBoardUserText
        val userProfileLayout = binding.yatchBoardUserLayout
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val view = YatchBoardUserItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userProfileId.text = userList[position].nickname
        if(userClicked[position])
            holder.userProfileLayout.setBackgroundResource(R.drawable.board_user_text_background_clicked)

        holder.userProfileLayout.setOnClickListener {
            onBoardCallback.onUserListChanged(position)
        }
        Log.d("user view", position.toString())
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}
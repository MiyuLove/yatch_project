package com.exercise.dailyyatchproject.YatchFragment.RecyclerAdapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.exercise.dailyyatchproject.LocalDatabase.Entity.UserEntity
import com.exercise.dailyyatchproject.YatchFragment.OnUserListCallback
import com.exercise.dailyyatchproject.databinding.UserBoardItemBinding

class UserRecyclerAdapter (private val userList : List<UserEntity>,
private val onUserListCallback: OnUserListCallback):
    RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder>() {

    class ViewHolder(binding : UserBoardItemBinding) : RecyclerView.ViewHolder(binding.root){
        val userProfileId : TextView = binding.userBoardProfileNickname
        val userProfileImage : ImageView = binding.userBoardProfileImage
        val deleteButton : ImageButton = binding.userBoardProfileDeleteButton
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, ): ViewHolder {
        val view = UserBoardItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userProfileId.text = userList[position].text
        holder.deleteButton.setOnClickListener {
            onUserListCallback.onUserListDeleted(userList[position])
        }
        try {
            holder.userProfileImage.setImageBitmap(userList[position].profilePhoto)
        }catch (e:Exception){
            Log.d("user", "error")
        }

    }
    override fun getItemCount(): Int {
        return userList.size
    }
}


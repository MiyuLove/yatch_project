package com.exercise.dailyyatchproject.YatchFragment.RecyclerAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.exercise.dailyyatchproject.LocalDatabase.Entity.ResultEntity
import com.exercise.dailyyatchproject.databinding.ResultBoardItemBinding
import java.text.SimpleDateFormat
import java.util.Locale

class HistoryRecyclerAdapter(private val resultList : List<ResultEntity>, private val itemClick : (Int) -> Unit):RecyclerView.Adapter<HistoryRecyclerAdapter.ViewHolder>() {
    class ViewHolder(binding : ResultBoardItemBinding) : RecyclerView.ViewHolder(binding.root){
        val winnerDate : TextView = binding.textItemDate
        val winnerName : TextView = binding.textItemWinner
        val winnerButton : Button = binding.textItemButton
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val view = ResultBoardItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.winnerDate.text = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(resultList[position].dateTime)
        holder.winnerName.text = "Winner : "+ resultList[position].winner
        holder.winnerButton.setOnClickListener {
            itemClick(position)
        }
    }
    override fun getItemCount(): Int {
        return resultList.size
    }
}

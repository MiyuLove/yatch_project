package com.exercise.dailyyatchproject.YatchFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.dailyyatchproject.R
import com.exercise.dailyyatchproject.YatchFragment.RecyclerAdapters.HistoryRecyclerAdapter
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.HistoryViewModel
import com.exercise.dailyyatchproject.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment(){
    private lateinit var binding : FragmentHistoryBinding
    private lateinit var navController: NavController
    private lateinit var viewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[HistoryViewModel::class.java]
        viewModel.read()
        navController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater,container,false)
        initView()
        return binding.root
    }

    private fun initView(){
        binding.historyResultButton.setOnClickListener {
            viewModel.deleteAllData()
        }
        initRecyclerView()
    }

    private fun initRecyclerView(){
        val recyclerItemClick ={itemPosition : Int ->
            navController.navigate(R.id.action_historyFragment_to_historyDataFragment)
        }

        viewModel.resultEntityList.observe(viewLifecycleOwner, Observer {
            binding.historyResultEmpty.isVisible = it.isEmpty()
            binding.historyResultBoard.adapter = HistoryRecyclerAdapter(it,recyclerItemClick)
            val layoutManager = LinearLayoutManager(this.context)
            binding.historyResultBoard.layoutManager = layoutManager
            binding.historyResultBoard.scrollToPosition(it.size-1)
        })
    }
}
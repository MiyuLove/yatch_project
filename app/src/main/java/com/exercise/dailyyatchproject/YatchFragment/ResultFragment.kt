package com.exercise.dailyyatchproject.YatchFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.dailyyatchproject.LocalDatabase.Entity.ResultEntity
import com.exercise.dailyyatchproject.MainActivity
import com.exercise.dailyyatchproject.R
import com.exercise.dailyyatchproject.YatchFragment.RecyclerAdapters.BoardRankingRecyclerAdapter
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.HistoryViewModel
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.MainViewModel
import com.exercise.dailyyatchproject.databinding.FragmentResultBinding
import java.util.Calendar

class ResultFragment : Fragment() {
    private lateinit var binding : FragmentResultBinding
    private lateinit var viewModel : HistoryViewModel
    private lateinit var mainViewModel : MainViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = (requireActivity() as MainActivity).mainViewModel
        viewModel = HistoryViewModel()
        viewModel.setUserSortedList(mainViewModel.getMainUserData().toMutableList())
        //room에 저장
        navController = findNavController()

        requireActivity().onBackPressedDispatcher.addCallback(this){
            navController.popBackStack(R.id.menuFragment, false)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentResultBinding.inflate(inflater, container,false)
        initView()
        return binding.root
    }

    private fun initView(){
        binding.resultRecyclerView.adapter = BoardRankingRecyclerAdapter(viewModel.getUserList())

        binding.resultRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)

        binding.resultReplayButton.setOnClickListener {
            mainViewModel.initiateScore()
            navController.popBackStack(R.id.onlyBoardFragment, false)
        }
    }
}
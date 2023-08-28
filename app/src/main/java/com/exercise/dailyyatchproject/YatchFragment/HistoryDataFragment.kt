package com.exercise.dailyyatchproject.YatchFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.dailyyatchproject.MainActivity
import com.exercise.dailyyatchproject.R
import com.exercise.dailyyatchproject.YatchFragment.RecyclerAdapters.BoardRankingRecyclerAdapter
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.DataViewModel
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.HistoryViewModel
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.MainViewModel
import com.exercise.dailyyatchproject.databinding.FragmentHistoryBinding
import com.exercise.dailyyatchproject.databinding.FragmentHistoryDataBinding
import java.text.SimpleDateFormat
import java.util.Locale

class HistoryDataFragment : Fragment() {
    private lateinit var binding : FragmentHistoryDataBinding
    private lateinit var navController: NavController
    private lateinit var viewModel: DataViewModel
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = (requireActivity() as MainActivity).mainViewModel
        viewModel = ViewModelProvider(this)[DataViewModel::class.java]
        viewModel.setResultEntity(mainViewModel.getResultEntityList()!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHistoryDataBinding.inflate(inflater, container, false)
        navController = findNavController()
        initView()
        return binding.root
    }
    var deleteButton = true

    private fun initView() = with(binding){
        historyDataTitleText.text =
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(viewModel.resultEntity.value!!.dateTime)
        historyDataDeleteButton.setOnClickListener {
            if(deleteButton) {
                viewModel.deleteResult()
                navController.popBackStack(R.id.historyFragment,false)
            }
            deleteButton = false
        }

        //UI thread와 delete 작업을 분리하기 위한 작업
        //안 하면 delete 시 recyclerView로 인해 null error...
        viewModel.resultEntity.observe(viewLifecycleOwner) {
            historyDataRecyclerView.adapter = BoardRankingRecyclerAdapter(viewModel.resultEntityToMainUserDataList(it))
            historyDataRecyclerView.layoutManager = LinearLayoutManager(context)
        }


//        binding.resultRecyclerView.adapter = BoardRankingRecyclerAdapter(viewModel.getUserList())
//        binding.resultRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)
    }
}
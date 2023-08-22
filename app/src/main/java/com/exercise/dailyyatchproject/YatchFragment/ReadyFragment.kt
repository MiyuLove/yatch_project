package com.exercise.dailyyatchproject.YatchFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.exercise.dailyyatchproject.MainActivity
import com.exercise.dailyyatchproject.R
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.MainViewModel
import com.exercise.dailyyatchproject.databinding.FragmentReadyBinding

class ReadyFragment : Fragment() {
    private lateinit var binding : FragmentReadyBinding
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (requireActivity() as MainActivity).mainViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentReadyBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }
    private fun initView(){
        binding.readyTestNavDiceWithBoard.setOnClickListener{
        //    viewModel.setGameMode(0)
        //    Navigation.findNavController(binding.root).navigate(R.id.action_readyFragment_to_userFragment)
        }
        binding.readyTestNavBoard.setOnClickListener {
            viewModel.setGameMode(1)
            Navigation.findNavController(binding.root).navigate(R.id.action_readyFragment_to_userFragment)
        }
    }
}
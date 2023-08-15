package com.exercise.dailyyatchproject.YatchFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.exercise.dailyyatchproject.R
import com.exercise.dailyyatchproject.databinding.FragmentReadyBinding

class ReadyFragment : Fragment() {
    private lateinit var binding : FragmentReadyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            Navigation.findNavController(binding.root).navigate(R.id.action_readyFragment_to_diceWithBoardFragment)
        }
        binding.readyTestNavBoard.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_readyFragment_to_onlyBoardFragment)
        }
    }
}
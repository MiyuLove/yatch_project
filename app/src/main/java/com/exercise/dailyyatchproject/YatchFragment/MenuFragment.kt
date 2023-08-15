package com.exercise.dailyyatchproject.YatchFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.exercise.dailyyatchproject.R
import com.exercise.dailyyatchproject.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    private lateinit var binding : FragmentMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    private fun initView(){
        binding.menuTestNavGame.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_menuFragment_to_readyFragment)
        }

        binding.menuTestNavHistory.setOnClickListener{
            Navigation.findNavController(binding.root).navigate(R.id.action_menuFragment_to_historyFragment)
        }
    }
}
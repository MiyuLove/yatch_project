package com.exercise.dailyyatchproject.YatchFragment

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.exercise.dailyyatchproject.R
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.MainViewModel
import com.exercise.dailyyatchproject.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    private lateinit var binding : FragmentMenuBinding
    private lateinit var navController: NavController
    private lateinit var viewModel: MainViewModel
    private var exitButtonClicked = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this){ exitButton() }
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        navController = findNavController()

        initView()
        return binding.root
    }

    private fun initView(){
        binding.menuTestNavGame.setOnClickListener {
            navController.navigate(R.id.action_menuFragment_to_userFragment)
        }

        binding.menuTestNavHistory.setOnClickListener {
            navController.navigate(R.id.action_menuFragment_to_historyFragment)
        }
    }

    private fun exitButton(){
        exitButtonClicked ++
        if(exitButtonClicked > 1){
            System.runFinalization()
            System.exit(0)
        }
        Toast.makeText(binding.root.context,"종료 버튼을 한번 더\n눌러주세요", Toast.LENGTH_SHORT).show()

        Handler().postDelayed({
            exitButtonClicked = 0
        },3000)
    }
}
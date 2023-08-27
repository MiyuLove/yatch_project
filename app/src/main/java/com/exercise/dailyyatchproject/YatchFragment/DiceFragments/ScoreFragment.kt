package com.exercise.dailyyatchproject.YatchFragment.DiceFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.navigation.fragment.navArgs
import com.exercise.dailyyatchproject.MainActivity
import com.exercise.dailyyatchproject.R
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.DiceViewModel
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.MainViewModel
import com.exercise.dailyyatchproject.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {
    private lateinit var binding : FragmentScoreBinding
    private lateinit var diceViewModel: DiceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        diceViewModel = (requireActivity() as MainActivity).mainViewModel.getDiceViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentScoreBinding.inflate(inflater, container, false)

        initView()

        return binding.root
    }

    private fun initView(){
        binding.diceScoreAfterButton.setOnClickListener {
        }
        binding.diceScoreBeforeButton.setOnClickListener {
            diceViewModel.userPlus()
        }

        diceViewModel.userTurn.observe(viewLifecycleOwner, Observer {
            binding.diceScoreText.text = it.toString()
        })
    }
}
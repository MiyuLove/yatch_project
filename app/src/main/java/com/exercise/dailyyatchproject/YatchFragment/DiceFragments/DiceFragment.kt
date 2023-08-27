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
import com.exercise.dailyyatchproject.MainActivity
import com.exercise.dailyyatchproject.R
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.BoardViewModel
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.CastViewModel
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.DiceViewModel
import com.exercise.dailyyatchproject.databinding.FragmentDiceBinding
import com.exercise.dailyyatchproject.databinding.FragmentDiceWithBoardBinding

class DiceFragment : Fragment() {
    private lateinit var binding : FragmentDiceBinding
    private lateinit var diceViewModel: DiceViewModel
//    private lateinit var viewModel : CastViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        diceViewModel = (requireActivity() as MainActivity).mainViewModel.getDiceViewModel()
//        viewModel = ViewModelProvider(this)[CastViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDiceBinding.inflate(inflater, container, false)

        initView()
        return binding.root
    }

    private fun initView(){
        initButton()
    }

    private fun initButton(){
        val diceButtonList = listOf(
            binding.diceBoardDialogDice1,
            binding.diceBoardDialogDice2,
            binding.diceBoardDialogDice3,
            binding.diceBoardDialogDice4,
            binding.diceBoardDialogDice5,
        )

        for(i in diceButtonList.indices)
            diceButtonList[i].setOnClickListener {
                diceViewModel.clickDice(i)
            }

        binding.diceBoardDialogCastButton.setOnClickListener {
            diceViewModel.castingDice()
        }

        diceViewModel.diceList.observe(viewLifecycleOwner, Observer {
            Log.d("dice list", it.toString())
        })

        diceViewModel.selectList.observe(viewLifecycleOwner, Observer {
            Log.d("select list", it.toString())
        })

    }
}
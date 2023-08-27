package com.exercise.dailyyatchproject.YatchFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.exercise.dailyyatchproject.MainActivity
import com.exercise.dailyyatchproject.R
import com.exercise.dailyyatchproject.YatchFragment.DialogPackage.BoardRankingDialog
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.DiceViewModel
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.MainViewModel
import com.exercise.dailyyatchproject.databinding.FragmentDiceWithBoardBinding

class DiceWithBoardFragment : Fragment(){
    lateinit var binding : FragmentDiceWithBoardBinding
    private val diceViewModel : DiceViewModel by viewModels()
    private lateinit var mainViewModel : MainViewModel
    private lateinit var navController: NavController
    private lateinit var childNavController : NavController
    private var navigatorFlag = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    private fun initViewModel(){
        mainViewModel = (requireActivity() as MainActivity).mainViewModel
        val mutableList = mutableListOf<String>()
        mainViewModel.getMainUserData().forEach {
            mutableList.add(it.nickname)
        }
        mainViewModel.setDiceViewModel(diceViewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDiceWithBoardBinding.inflate(inflater, container, false)
        initView()

        Log.d("board parent", diceViewModel.toString())
        return binding.root
    }

    private fun initView(){
        initButton()
        initSelectedDiceButton()
        childNavController = childFragmentManager.findFragmentById(R.id.dice_board_nav_fragment)!!.findNavController()
        navController = findNavController()
    }

    private fun initButton(){
        binding.diceBoardDiceButton.setOnClickListener {
            diceViewModel.userPlus()
            if(navigatorFlag) {
                childNavController.navigate(R.id.action_scoreFragment_to_diceFragment)
            }
            else {
                childNavController.navigate(R.id.action_diceFragment_to_scoreFragment)
            }
            navigatorFlag = !navigatorFlag
        }

        binding.diceBoardScoreButton.setOnClickListener {
            val diceScoreDialog = BoardRankingDialog(binding.root.context, mainViewModel.getMainUserData())
            diceScoreDialog.show()
        }

        binding.diceBoardSettingButton.setOnClickListener {
            diceViewModel.initDiceList()
            diceViewModel.initSelecttList()
        }


    }

    private fun initSelectedDiceButton(){
        val selectedDiceList = listOf(
            binding.diceBoardSelectedDice1,
            binding.diceBoardSelectedDice2,
            binding.diceBoardSelectedDice3,
            binding.diceBoardSelectedDice4,
            binding.diceBoardSelectedDice5,
        )
        for(i in selectedDiceList.indices){
            selectedDiceList[i].setOnClickListener {
                diceViewModel.clickSelectedDice(i)
            }
        }
    }
}
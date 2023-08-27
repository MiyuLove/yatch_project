package com.exercise.dailyyatchproject.YatchFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.exercise.dailyyatchproject.MainActivity
import com.exercise.dailyyatchproject.R
import com.exercise.dailyyatchproject.YatchFragment.DialogPackage.BoardRankingDialog
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.BoardViewModel
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.MainViewModel
import com.exercise.dailyyatchproject.databinding.BoardScoreLayoutBinding
import com.exercise.dailyyatchproject.databinding.FragmentOnlyBoardBinding

class OnlyBoardFragment : Fragment(), OnBoardCallback {
    private lateinit var binding : FragmentOnlyBoardBinding
    private lateinit var navController: NavController
    private lateinit var viewModel : BoardViewModel
    private lateinit var mainViewModel : MainViewModel
    private lateinit var scoreBinding : BoardScoreLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    private fun initViewModel(){
        mainViewModel = (requireActivity() as MainActivity).mainViewModel
        viewModel = ViewModelProvider(this)[BoardViewModel::class.java]
        viewModel.setUserData(mainViewModel.getMainUserData())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentOnlyBoardBinding.inflate(inflater, container, false)
        scoreBinding = BoardScoreLayoutBinding.inflate(inflater,container,false)
        binding.onlyBoardLayout.addView(scoreBinding.boardScoreConstraintLayout)
        navController = findNavController()
        initBindingView()
        initScoreBindingView()
        return binding.root
    }

    private fun initBindingView() = with(binding){
        onlyBoardCurrentScoreListButton.setOnClickListener {
            BoardRankingDialog(binding.root.context,viewModel.getUserData()).show()
        }

        onlyBoardResultButton.setOnClickListener {
            mainViewModel.resultData(viewModel.getUserData())
            navController.navigate(R.id.action_onlyBoardFragment_to_resultFragment)
        }

        viewModel.currentName.observe(viewLifecycleOwner) {
            onlyBoardTextUser.text = it
        }
    }

    private fun initScoreBindingView() = with(scoreBinding){
        val buttonList = listOf(
            boardScoreButtonNum1,
            boardScoreButtonNum2,
            boardScoreButtonNum3,
            boardScoreButtonNum4,
            boardScoreButtonNum5,
            boardScoreButtonNum6,
            boardScoreTripleButton,
            boardScoreQuadraButton,
            boardScoreFullHouseButton,
            boardScoreChoiceButton,
            boardScoreSmallStraightButton,
            boardScoreLargeStraightButton,
            boardScoreYatchButton,
        )

        for(i in buttonList.indices){
            buttonList[i].boardButton.setOnClickListener {
                viewModel.changeScoreList(i, 20)
            }
        }

        viewModel.scoreList.observe(viewLifecycleOwner, Observer { scoreList ->
            for(i in scoreList.indices){
                buttonList[i].boardButtonBoxText.text = scoreList[i].toString()
            }
        })

        viewModel.bonusScore.observe(viewLifecycleOwner){
            val bonus = "뽀나스! $it"
            val nonBonus = "뽀나스! $it"
            if(it != 0)boardScoreBonusText.text = bonus
            else boardScoreBonusText.text = nonBonus
        }

        boardScoreAfterButton.setOnClickListener {
            viewModel.changeTurn()
        }

        boardScoreBeforeButton.setOnClickListener {
            viewModel.beforeTurn()
        }

        viewModel.score.observe(viewLifecycleOwner) {
            val string = "점수 : $it"
            boardScoreScoreText.text = string
        }

    }

    override fun onDialogDismissed(index : Int, value: Int) = with(viewModel) {
        changeScoreList(index,value)
    }

    override fun onUserListChanged(index: Int) = with(viewModel) {
        if(index == -1) {
            changeTurn()
        }
        else {
            changeTurn(index)
        }

    }
}

interface OnBoardCallback {//일단은 납두자.
    fun onDialogDismissed(index : Int, value : Int)
    fun onUserListChanged(index : Int)
}
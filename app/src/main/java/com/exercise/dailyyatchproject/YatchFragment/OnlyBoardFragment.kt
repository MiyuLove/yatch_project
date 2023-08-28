package com.exercise.dailyyatchproject.YatchFragment

import android.os.Bundle
import android.util.Log
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
import com.exercise.dailyyatchproject.YatchFragment.DialogPackage.BoardKeyboardDialog
import com.exercise.dailyyatchproject.YatchFragment.DialogPackage.BoardRankingDialog
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.BoardViewModel
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.MainViewModel
import com.exercise.dailyyatchproject.databinding.BoardScoreLayoutBinding
import com.exercise.dailyyatchproject.databinding.FragmentOnlyBoardBinding
import com.exercise.dailyyatchproject.databinding.KeyboardDialogBinding

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
            val userNickname = editNickname(it)
            onlyBoardTextUser.text = userNickname
        }
    }
    private fun editNickname(nickname : String = "") : String{
        if(nickname.length <= 6){
            return nickname
        }
        var omittedNickname = ""

        for(i in 0..3){
            omittedNickname += nickname[i]
        }
        for(i in 0..2){
            omittedNickname +="."
        }

        return omittedNickname
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

        val buttonImageList = listOf(
            R.drawable.dice_num1,
            R.drawable.dice_num2,
            R.drawable.dice_num3,
            R.drawable.dice_num4,
            R.drawable.dice_num5,
            R.drawable.dice_num6,
            R.drawable.dice_tripple,
            R.drawable.dice_quadra,
            R.drawable.dice_full_house,
            R.drawable.dice_choice,
            R.drawable.dice_small_straight,
            R.drawable.dice_large_straight,
            R.drawable.dice_yatch,
        )
        val keyBoardDialog= BoardKeyboardDialog(binding.root.context,this@OnlyBoardFragment)

        for(i in buttonList.indices){
            buttonList[i].boardButton.setOnClickListener {
                keyBoardDialog.show(i)
            }
        }

        buttonList[buttonList.size-1].boardButtonBoxImage.setImageResource(R.drawable.dice_yatch)
        viewModel.scoreList.observe(viewLifecycleOwner, Observer { scoreList ->
            for(i in scoreList.indices){
                buttonList[i].boardButtonBoxText.text = scoreList[i].toString()
                buttonList[i].boardButtonBoxImage.setImageResource(buttonImageList[i])
            }
        })

        viewModel.bonusScore.observe(viewLifecycleOwner){
            val bonus = "뽀나스! $it"
            val nonBonus = "뽀나스 X"
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
            val string = "점수\n$it"
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
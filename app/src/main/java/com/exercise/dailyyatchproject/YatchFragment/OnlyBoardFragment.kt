package com.exercise.dailyyatchproject.YatchFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.exercise.dailyyatchproject.MainActivity
import com.exercise.dailyyatchproject.R
import com.exercise.dailyyatchproject.YatchFragment.Board.BoardScoreAdapter
import com.exercise.dailyyatchproject.YatchFragment.DialogPackage.BoardKeyboardDialog
import com.exercise.dailyyatchproject.YatchFragment.DialogPackage.BoardRankingDialog
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.BoardViewModel
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.MainViewModel
import com.exercise.dailyyatchproject.databinding.BoardButtonBoxBinding
import com.exercise.dailyyatchproject.databinding.BoardScoreLayoutBinding
import com.exercise.dailyyatchproject.databinding.FragmentOnlyBoardBinding
import com.exercise.dailyyatchproject.databinding.KeyboardDialogBinding

class OnlyBoardFragment : Fragment(), OnBoardCallback {
    private lateinit var binding : FragmentOnlyBoardBinding
    private lateinit var navController: NavController
    private lateinit var viewModel : BoardViewModel
    private lateinit var mainViewModel : MainViewModel

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
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnlyBoardBinding.inflate(inflater, container, false)

        binding.onlyBoardLayout.addView(setScoreBindingView(inflater, container))
        navController = findNavController()
        initBindingView()

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

    private fun setScoreBindingView(inflater: LayoutInflater, container: ViewGroup?) : ConstraintLayout {
        val boardScoreAdapter = BoardScoreAdapter(inflater,container,false)

        setScoreText(boardScoreAdapter)
        setScoreButton(boardScoreAdapter)
        setBottomButton(boardScoreAdapter)

        boardScoreAdapter.getScoreBoard()
        return boardScoreAdapter.getScoreBoard()
    }
    private fun setScoreText(boardScoreAdapter: BoardScoreAdapter){
        viewModel.scoreList.observe(viewLifecycleOwner){
            boardScoreAdapter.setScoreButtonTextGroup(it)
        }
        viewModel.bonusScore.observe(viewLifecycleOwner){
            val bonus = "보너스! 35"
            val nonBonus = "합계 $it"
            if(it >= 63)boardScoreAdapter.setBonusScore(bonus)
            else boardScoreAdapter.setBonusScore(nonBonus)
        }

        viewModel.score.observe(viewLifecycleOwner){
            boardScoreAdapter.setScore("점수\n$it")
        }
    }
    private fun setScoreButton(boardScoreAdapter: BoardScoreAdapter){
        val keyBoardDialog= BoardKeyboardDialog(binding.root.context,this@OnlyBoardFragment)

        boardScoreAdapter.setScoreButtonGroup {
            keyBoardDialog.show(it)
        }
    }
    private fun setBottomButton(boardScoreAdapter: BoardScoreAdapter){
        val leftClicked = {
            viewModel.beforeTurn()
        }

        val rightClicked = {
            viewModel.changeTurn()
        }
        boardScoreAdapter.setBottomButton(leftClicked, rightClicked)
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
package com.exercise.dailyyatchproject.YatchFragment

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.exercise.dailyyatchproject.MainActivity
import com.exercise.dailyyatchproject.R
import com.exercise.dailyyatchproject.YatchFragment.Board.BoardScoreAdapter
import com.exercise.dailyyatchproject.YatchFragment.DialogPackage.BoardRankingDialog
import com.exercise.dailyyatchproject.YatchFragment.DialogPackage.DiceBoardDialog
import com.exercise.dailyyatchproject.YatchFragment.DialogPackage.DiceBoardFragmentDialog
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.BoardViewModel
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.DiceViewModel
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.MainViewModel
import com.exercise.dailyyatchproject.databinding.FragmentDiceWithBoardBinding

class DiceWithBoardFragment : Fragment(), OnDiceCallback{
    lateinit var binding : FragmentDiceWithBoardBinding
    lateinit var viewModel : BoardViewModel
    private lateinit var mainViewModel : MainViewModel
    private lateinit var navController: NavController

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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDiceWithBoardBinding.inflate(inflater, container, false)
        binding.diceBoardLayout.addView(setScoreBindingView(inflater,container))
        initView()

        return binding.root
    }

    private fun initView() = with(binding){
        navController = findNavController()
        //val diceBoardDialog = DiceBoardDialog(binding.root.context)
        val scoreBoardDialog = BoardRankingDialog(binding.root.context, viewModel.getUserData())

        diceBoardDiceButton.setOnClickListener {
            DiceBoardFragmentDialog(this@DiceWithBoardFragment).show(requireFragmentManager(),"dialog_tag", viewModel.castCount)

        }

        diceBoardScoreButton.setOnClickListener {
            scoreBoardDialog.show()
        }

        initSelectedDiceButton()
    }

    private fun setScoreBindingView(inflater: LayoutInflater, container: ViewGroup?) : ConstraintLayout {
        val boardScoreAdapter = BoardScoreAdapter(inflater,container,false)

        setScoreText(boardScoreAdapter)
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

    private fun setBottomButton(boardScoreAdapter: BoardScoreAdapter){
        val leftClicked = {
            viewModel.beforeTurn()
        }

        val rightClicked = {
            viewModel.changeTurn()
        }
        boardScoreAdapter.setBottomButton(leftClicked, rightClicked)
    }
    private fun initSelectedDiceButton() = with(binding){
        val selectedDiceList = listOf(
            diceBoardSelectedDice1,
            diceBoardSelectedDice2,
            diceBoardSelectedDice3,
            diceBoardSelectedDice4,
            diceBoardSelectedDice5,
        )

        val imageList = listOf(
            R.drawable.ic_launcher_foreground,
            R.drawable.dice_num1,
            R.drawable.dice_num2,
            R.drawable.dice_num3,
            R.drawable.dice_num4,
            R.drawable.dice_num5,
            R.drawable.dice_num6,
        )

        viewModel.diceList.observe(viewLifecycleOwner){
            for(i in it.indices){
                selectedDiceList[i].setImageResource(imageList[it[i]])
            }
        }
    }

    override fun onDialogDismissed(count: Int, value: List<Int>) {
        viewModel.changeDiceList(count, value)
    }
}

interface OnDiceCallback{
    fun onDialogDismissed(count : Int, value : List<Int>)
}
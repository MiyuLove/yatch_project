package com.exercise.dailyyatchproject.YatchFragment.Board

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.exercise.dailyyatchproject.R
import com.exercise.dailyyatchproject.databinding.BoardButtonBoxBinding
import com.exercise.dailyyatchproject.databinding.BoardScoreLayoutBinding

class BoardScoreAdapter(inflater: LayoutInflater, container: ViewGroup?, attatch : Boolean = false) {
    private val binding : BoardScoreLayoutBinding
    private lateinit var buttonList : List<BoardButtonBoxBinding>
    private val scoreBoard : ConstraintLayout

    init {
        binding = BoardScoreLayoutBinding.inflate(inflater, container, attatch)
        scoreBoard = binding.boardScoreConstraintLayout
        initView()
    }

    fun getScoreBoard() = scoreBoard

    private fun initView()= with(binding){
        buttonList = listOf(
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
        setScoreButtonImageGroup()
    }

    private fun setScoreButtonImageGroup(){
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

        var index = 0
        buttonList.forEach {
            it.boardButtonBoxImage.setImageResource(buttonImageList[index])
            index ++
        }
    }

    private fun setScoreButtonImageGroup(imageList : List<Int>){
        for(i in buttonList.indices){
            buttonList[i].boardButtonBoxImage.setImageResource(imageList[i])
        }
    }

    fun setScoreButtonTextGroup(numberList : List<Int>){
        var index = 0
        buttonList.forEach {
            it.boardButtonBoxText.text = numberList[index].toString()
            index ++
        }
    }
    fun setBonusScore(bonusScore : String) {
        binding.boardScoreBonusText.text = bonusScore
    }
    fun setScore(score : String){
        binding.boardScoreScoreText.text = score
    }
    //button에 Int 람다 호출을 통해 버튼 index와 람다 동기화
    fun setScoreButtonGroup(clicked : (Int) -> Unit){
        for(i in buttonList.indices){
            buttonList[i].boardButton.setOnClickListener {
                clicked(i)
            }
        }
    }
    fun setBottomButton(leftButton : () -> Unit, rightButton : () -> Unit){
        binding.boardScoreBeforeButton.setOnClickListener {
            leftButton()
        }
        binding.boardScoreAfterButton.setOnClickListener {
            rightButton()
        }
    }
}
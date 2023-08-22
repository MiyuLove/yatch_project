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
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.exercise.dailyyatchproject.R
import com.exercise.dailyyatchproject.UtilSupply.GlobalApplication.Companion.getDeviceHeight
import com.exercise.dailyyatchproject.UtilSupply.GlobalApplication.Companion.getDeviceWidth
import com.exercise.dailyyatchproject.YatchFragment.Board.BoardBase
import com.exercise.dailyyatchproject.YatchFragment.Board.BoardKeyboardDialog
import com.exercise.dailyyatchproject.YatchFragment.Board.BoardRenderingBox
import com.exercise.dailyyatchproject.YatchFragment.Board.BoardText
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.BoardViewModel
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.MainViewModel
import com.exercise.dailyyatchproject.databinding.BoardButtonBoxBinding
import com.exercise.dailyyatchproject.databinding.FragmentOnlyBoardBinding

class OnlyBoardFragment : Fragment(), OnDialogDismissListener {
    private lateinit var binding : FragmentOnlyBoardBinding
    private lateinit var navController: NavController
    private lateinit var viewModel : BoardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[BoardViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentOnlyBoardBinding.inflate(inflater, container, false)
        navController = findNavController()
        initView(inflater, container)

        return binding.root
    }

    private fun initView(inflater: LayoutInflater, container: ViewGroup?){
        binding.boardNext.setOnClickListener {
            //navController.navigate(R.id.action_onlyBoardFragment_to_resultFragment)
        }
        initBoardButton(inflater,container)
    }

    private fun initBoardButton(inflater: LayoutInflater, container: ViewGroup?){
        val boardRenderingBox = BoardRenderingBox(getDeviceWidth(),(getDeviceHeight() * 0.55).toInt(), inflater, container)
        val boardButtonList = boardRenderingBox.getBoardButtonList()
        val boardTextList = boardRenderingBox.boardTextList

        for(i in boardButtonList.indices){
            boardButtonList[i].setOnClickListener {
                val dlg = BoardKeyboardDialog(binding.root.context, this)
                dlg.setDialogDismissListener(this)
                dlg.show(i)
            }
            binding.onlyBoardLayout.addView(boardButtonList[i])
        }

        viewModel.textList.observe(this.viewLifecycleOwner, Observer {
            for(i in boardTextList.indices){
                boardTextList[i].boardButtonBoxText.text = it[i].toString()
            }
        })

        viewModel.score.observe(this.viewLifecycleOwner, Observer {
            binding.onlyBoardTextScore.text = it.toString()
        })
    }

    override fun onDialogDismissed(index : Int, value: Int) {
        viewModel.changeButtonTextList(index,value)
    }
}

interface OnDialogDismissListener {
    fun onDialogDismissed(index : Int, value : Int)
}
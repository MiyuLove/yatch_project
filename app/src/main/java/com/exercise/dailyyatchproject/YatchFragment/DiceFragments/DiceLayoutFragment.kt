package com.exercise.dailyyatchproject.YatchFragment.DiceFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavHostController
import com.exercise.dailyyatchproject.R
import com.exercise.dailyyatchproject.databinding.FragmentDiceLayoutBinding

class DiceLayoutFragment : Fragment() {
    private lateinit var binding : FragmentDiceLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDiceLayoutBinding.inflate(inflater, container, false)

        return binding.root
    }

}
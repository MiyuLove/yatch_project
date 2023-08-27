package com.exercise.dailyyatchproject.YatchFragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.exercise.dailyyatchproject.R
import com.exercise.dailyyatchproject.UtilSupply.UtilCode.Companion.ucu
import com.exercise.dailyyatchproject.databinding.FragmentIntroBinding

class IntroFragment : Fragment() {
    private var _binding : FragmentIntroBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentIntroBinding.inflate(inflater, container, false)

        initView()

        return binding.root
    }
    private fun initView(){
        binding.introTestNavButton.animate()
            .alpha(1f)
            .setDuration(1000)
            .withEndAction({

            })
    }
}
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
    private lateinit var binding : FragmentIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentIntroBinding.inflate(inflater, container, false)

        initView()

        return binding.root
    }
    private fun initView(){
        binding.introTestNavButton.setOnClickListener { asdf ->
            Navigation.findNavController(binding.root).navigate(R.id.action_IntroFragment_to_menuFragment)
        }
    }
}
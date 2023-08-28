package com.exercise.dailyyatchproject.YatchFragment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.dailyyatchproject.LocalDatabase.Entity.UserEntity
import com.exercise.dailyyatchproject.MainActivity
import com.exercise.dailyyatchproject.R
import com.exercise.dailyyatchproject.YatchFragment.Board.BoardUserDialog
import com.exercise.dailyyatchproject.YatchFragment.RecyclerAdapters.UserRecyclerAdapter
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.MainViewModel
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.UserViewModel
import com.exercise.dailyyatchproject.databinding.FragmentUserBinding
import kotlin.random.Random

class UserFragment : Fragment(), OnUserListCallback {
    lateinit var binding : FragmentUserBinding
    lateinit var viewModel : UserViewModel
    lateinit var mainViewModel : MainViewModel
    private var changing = false
    private var lockButton = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        initView()

        return binding.root
    }

    private fun initViewModel(){
        mainViewModel = (requireActivity() as MainActivity).mainViewModel
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        viewModel.read()
    }

    private fun initView() = with(binding){
        if(mainViewModel.getGameMode() == 0){
            userTitleText.text = "Dice Board"
        }else{
            userTitleText.text = "Only Board"
        }

        userAddUserButton.setOnClickListener {
            if(lockButton && !changing){
                changing = true
                val dlg = BoardUserDialog(binding.root.context, this@UserFragment)
                dlg.show()
            }
        }

        viewModel.userEntityList.observe(viewLifecycleOwner, Observer {
            userListLayout.adapter = UserRecyclerAdapter(it, this@UserFragment)
            userListLayout.scrollToPosition(it.size-1)
            lockButton = true
        })

        binding.userOnlyBoardButton.setOnClickListener {
            if(lockButton&&viewModel.userEntityList.value!!.isNotEmpty()) {
                mainViewModel.setMainUserData(viewModel.userEntityList.value!!)
                if(mainViewModel.getGameMode() == 0){
                    Navigation.findNavController(binding.root).navigate(R.id.action_userFragment_to_diceWithBoardFragment)
                }
                else{
                    Navigation.findNavController(binding.root).navigate(R.id.action_userFragment_to_onlyBoardFragment)
                }
            }
        }

        userDeleteUserButton.setOnClickListener {
            if(lockButton)
                viewModel.deleteAllData()
        }

        userListLayout.layoutManager = LinearLayoutManager(context)
    }

    override fun onUserListAdded(value: String) {
        viewModel.create(UserEntity(0,value,createBitmapForUserList()))
    }

    private fun createBitmapForUserList(): Bitmap {
        val bitmapOption = BitmapFactory.Options()
        val resourceImageList = listOf(
            R.drawable.dice_num1,
            R.drawable.dice_num2,
            R.drawable.dice_num3,
            R.drawable.dice_num4,
            R.drawable.dice_num5,
            R.drawable.dice_num6,
        )
        bitmapOption.inSampleSize = 2

        return BitmapFactory.decodeResource(resources, resourceImageList[Random.nextInt(0,6)], bitmapOption)
    }

    override fun onUserListDeleted(userEntity: UserEntity) {
        viewModel.delete(userEntity)
    }

    override fun onUserAddedState(value: Boolean) {
        changing = value
    }
}


interface OnUserListCallback {
    fun onUserListAdded(value : String)
    fun onUserListDeleted(userEntity: UserEntity)
    fun onUserAddedState(value: Boolean)
}
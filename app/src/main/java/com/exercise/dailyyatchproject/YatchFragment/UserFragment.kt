package com.exercise.dailyyatchproject.YatchFragment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
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

class UserFragment : Fragment(), OnUserListCallback {
    lateinit var binding : FragmentUserBinding
    lateinit var viewModel : UserViewModel
    lateinit var mainViewModel : MainViewModel
    private var changing = false

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

    private fun initView(){
        if(mainViewModel.getGameMode() == 0){
            binding.userTitleText.text = "Dice Board"
        }else{
            binding.userTitleText.text = "Only Board"
        }

        binding.userAddUserButton.setOnClickListener {
            if(!changing) {
                changing = true
                val dlg = BoardUserDialog(binding.root.context, this)
                dlg.show()
            }
        }

        viewModel.userEntityList.observe(viewLifecycleOwner, Observer {
            binding.userListLayout.adapter = UserRecyclerAdapter(it, this)
            binding.userListLayout.scrollToPosition(it.size-1)
        })

        //setMainUserData 돌 때 button 누르면 튕긴다 그 이유를 찾자
        binding.userOnlyBoardButton.setOnClickListener {
            if(viewModel.userEntityList.value!!.isNotEmpty()) {
                mainViewModel.setMainUserData(viewModel.userEntityList.value!!)
                if(mainViewModel.getGameMode() == 0){
                    Navigation.findNavController(binding.root).navigate(R.id.action_userFragment_to_diceWithBoardFragment)
                }
                else{
                    Navigation.findNavController(binding.root).navigate(R.id.action_userFragment_to_onlyBoardFragment)
                }
            }
        }

        binding.userDeleteUserButton.setOnClickListener {
            viewModel.deleteAllData()
        }

        binding.userListLayout.layoutManager = LinearLayoutManager(this.context)
    }

    override fun onUserListAdded(value: String) {
        viewModel.create(UserEntity(0,value,createBitmapForUserList()))
    }

    private fun createBitmapForUserList(): Bitmap {
        val bitmapOption = BitmapFactory.Options()
        bitmapOption.inSampleSize = 2

        return BitmapFactory.decodeResource(resources, R.drawable.one_one_1, bitmapOption)
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
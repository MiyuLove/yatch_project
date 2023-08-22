package com.exercise.dailyyatchproject.YatchFragment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.dailyyatchproject.LocalDatabase.Entity.UserEntity
import com.exercise.dailyyatchproject.MainActivity
import com.exercise.dailyyatchproject.R
import com.exercise.dailyyatchproject.YatchFragment.Board.BoardKeyboardDialog
import com.exercise.dailyyatchproject.YatchFragment.Board.BoardUserDialog
import com.exercise.dailyyatchproject.YatchFragment.RecyclerAdapters.HistoryRecyclerAdapter
import com.exercise.dailyyatchproject.YatchFragment.RecyclerAdapters.UserRecyclerAdapter
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.MainViewModel
import com.exercise.dailyyatchproject.YatchFragment.YatchViewModel.UserViewModel
import com.exercise.dailyyatchproject.databinding.FragmentUserBinding
import java.lang.Exception

class UserFragment : Fragment(), OnUserListCallback {
    lateinit var binding : FragmentUserBinding
    lateinit var viewModel : UserViewModel
    lateinit var mainViewModel : MainViewModel

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

    //The implementation must be done within 'onCreate'
    private fun initViewModel(){
        mainViewModel = (requireActivity() as MainActivity).mainViewModel
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        viewModel.read()
    }

    private fun initView(){
        /*
        binding.userAddUserButton.setOnClickListener {
            val bitmapOption = BitmapFactory.Options()
            bitmapOption.inSampleSize = 2
            bitmap = BitmapFactory.decodeResource(resources, R.drawable.one_one_1,bitmapOption)
            viewModel.create(UserEntity(0,q.toString(),bitmap))
            q++
        }

         */

        binding.userAddUserButton.setOnClickListener {
            val dlg = BoardUserDialog(binding.root.context, this)
            dlg.show()
        }

        viewModel.userEntityList.observe(viewLifecycleOwner, Observer {
            binding.userListLayout.adapter = UserRecyclerAdapter(it)
            binding.userListLayout.scrollToPosition(it.size-1)
        })

        binding.userOnlyBoardButton.setOnClickListener {
            //if(viewModel.userEntityList.value!!.isNotEmpty()) {
                mainViewModel.setMainUserData(viewModel.userEntityList.value!!)
                Navigation.findNavController(binding.root).navigate(R.id.action_userFragment_to_onlyBoardFragment)
            //}
        }

        binding.userDeleteUserButton.setOnClickListener {
            viewModel.deleteAllData()
        }

        binding.userListLayout.layoutManager = LinearLayoutManager(this.context)
    }

    override fun onUserListAdded(value: String) {
        viewModel.create(UserEntity(0,value,createBitmapForUserList()))
    }

    private fun createBitmapForUserList() : Bitmap{
        val bitmapOption = BitmapFactory.Options()
        bitmapOption.inSampleSize = 2
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.one_one_1,bitmapOption)

        return bitmap
    }

    override fun onUserListAllDeleted() {
        TODO("Not yet implemented")
    }

    override fun onUserListDeleted(index: Int) {
        TODO("Not yet implemented")
    }
}


interface OnUserListCallback {
    fun onUserListAdded(value : String)
    fun onUserListAllDeleted()
    fun onUserListDeleted(index: Int)
}
package com.kamal.chatapplication.home

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.islami.base.BaseActivity
import com.kamal.chatapplication.R
import com.kamal.chatapplication.addroom.AddRoomActivity
import com.kamal.chatapplication.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<ActivityHomeBinding,HomeViewModel>(),HomeNavigator {
    lateinit var roomsAdapter: RoomsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.homeViewModel = viewModel
        viewModel.navigator = this
        setUpViews()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.roomsLiveData.observe(this, Observer {
            roomsAdapter.changeData(it) //For refreshing the list of rooms
        })
    }

    override fun onStart() {
        super.onStart()
        viewModel.getRooms()
    }
    private fun setUpViews() {
        roomsAdapter = RoomsAdapter(listOf())
        viewDataBinding.roomRecyclerView.adapter = roomsAdapter
    }

    override fun intializeViewModel(): HomeViewModel {
        return ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_home
    }

    override fun openRoom() {
        val intent = Intent(this,AddRoomActivity::class.java)
        startActivity(intent)
    }
}
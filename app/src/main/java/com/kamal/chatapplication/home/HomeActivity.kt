package com.kamal.chatapplication.home

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.islami.base.BaseActivity
import com.kamal.chatapplication.R
import com.kamal.chatapplication.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<ActivityHomeBinding,HomeViewModel>(),HomeNavigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.homeViewModel = viewModel
        viewModel.navigator = this
    }

    override fun intializeViewModel(): HomeViewModel {
        return ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_home
    }
}
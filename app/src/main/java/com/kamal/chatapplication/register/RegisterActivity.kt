package com.kamal.chatapplication.register

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.islami.base.BaseActivity
import com.kamal.chatapplication.R
import com.kamal.chatapplication.databinding.ActivityRegisterBinding
import com.kamal.chatapplication.home.HomeActivity
import com.kamal.chatapplication.login.LoginActivity

class RegisterActivity : BaseActivity<ActivityRegisterBinding,RegisterViewModel>(),RegisterNavigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
        viewModel.navigator = this
    }

    override fun intializeViewModel(): RegisterViewModel {
        return ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_register
    }

    override fun goToLoginScreen() {
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }
}
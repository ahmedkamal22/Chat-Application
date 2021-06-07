package com.kamal.chatapplication.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.islami.base.BaseActivity
import com.kamal.chatapplication.R
import com.kamal.chatapplication.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding,LoginViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
    }

    override fun intializeViewModel(): LoginViewModel {
        return ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_login
    }
}
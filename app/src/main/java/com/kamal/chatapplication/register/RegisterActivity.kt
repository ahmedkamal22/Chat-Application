package com.kamal.chatapplication.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.islami.base.BaseActivity
import com.kamal.chatapplication.R
import com.kamal.chatapplication.databinding.ActivityRegisterBinding

class RegisterActivity : BaseActivity<ActivityRegisterBinding,RegisterViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
    }

    override fun intializeViewModel(): RegisterViewModel {
        return ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_register
    }
}
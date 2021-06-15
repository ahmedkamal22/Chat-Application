package com.kamal.chatapplication.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.islami.base.BaseFragment
import com.kamal.chatapplication.R
import com.kamal.chatapplication.databinding.ActivityRegisterBinding
import com.kamal.chatapplication.login.LoginActivity

class RegisterFragment:BaseFragment<ActivityRegisterBinding,RegisterViewModel>(),RegisterNavigator{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
//        val intent = Intent(this, LoginActivity::class.java)
//        startActivity(intent)
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }
}
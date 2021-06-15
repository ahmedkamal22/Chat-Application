package com.kamal.chatapplication.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.islami.base.BaseFragment
import com.kamal.chatapplication.R
import com.kamal.chatapplication.databinding.ActivityLoginBinding
import com.kamal.chatapplication.home.HomeActivity
import com.kamal.chatapplication.register.RegisterActivity

class LoginFragment:BaseFragment<ActivityLoginBinding,LoginViewModel>(),LoginNavigator {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.vm = viewModel
        viewModel.navigator = this
    }
    override fun intializeViewModel(): LoginViewModel {
        return ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_login
    }

    override fun goToRegister() {
//        val intent = Intent(this, RegisterActivity::class.java)
//        startActivity(intent)
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    override fun goToHome() {
//        val intent = Intent(this, HomeActivity::class.java)
//        startActivity(intent)
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }
}
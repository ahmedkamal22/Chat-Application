   package com.kamal.chatapplication.splach

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.islami.base.BaseFragment
import com.kamal.chatapplication.R
import com.kamal.chatapplication.databinding.ActivitySplachBinding

class SplachFragment:BaseFragment<ActivitySplachBinding,SplachViewModel>(),SplachNavigator {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.splach = viewModel
        viewModel.navigator = this
        Handler(Looper.getMainLooper())
            .postDelayed({
                goToLogin()
            },2000)
    }
    override fun intializeViewModel(): SplachViewModel {
        return ViewModelProvider(this).get(SplachViewModel::class.java)
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_splach
    }

    override fun goToLogin() {
//        val intent = Intent(this, LoginActivity::class.java)
//        startActivity(intent)
        findNavController().navigate(R.id.action_splachFragment_to_loginFragment)
//        finish()
        //TODO: replace finish with back action
    }
}
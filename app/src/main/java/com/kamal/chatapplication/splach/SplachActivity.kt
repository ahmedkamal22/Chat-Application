package com.kamal.chatapplication.splach

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import com.example.islami.base.BaseActivity
import com.kamal.chatapplication.R
import com.kamal.chatapplication.databinding.ActivitySplachBinding
import com.kamal.chatapplication.login.LoginActivity

class SplachActivity : BaseActivity<ActivitySplachBinding,SplachViewModel>(),SplachNavigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
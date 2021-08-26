package com.kamal.chatapplication.addroom

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.islami.base.BaseActivity
import com.kamal.chatapplication.R
import com.kamal.chatapplication.databinding.ActivityAddRoomBinding

class AddRoomActivity : BaseActivity<ActivityAddRoomBinding,AddRoomViewModel>(),AddRoomNavigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.addRoomViewModel = viewModel
        viewModel.navigator = this
        viewModel.roomAdded.observe(this, Observer {
            showDialog(title = "Added Room",message = "Room Added Successfully!!",
            posActionName = "ok",posAction = DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                    finish()
                },isCancallable = false)
        })
    }

    override fun intializeViewModel(): AddRoomViewModel {
        return ViewModelProvider(this).get(AddRoomViewModel::class.java)
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_add_room
    }
}
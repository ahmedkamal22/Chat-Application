package com.kamal.chatapplication.addroom

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.islami.base.BaseFragment
import com.kamal.chatapplication.R
import com.kamal.chatapplication.databinding.ActivityAddRoomBinding

class AddRoomFragment:BaseFragment<ActivityAddRoomBinding,AddRoomViewModel>(),AddRoomNavigator {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.addRoomViewModel = viewModel
        viewModel.navigator = this
        viewModel.roomAdded.observe(viewLifecycleOwner, Observer {
            showDialog(title = "Added Room",message = "Room Added Successfully!!",
                posActionName = "ok",posAction = DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                   // finish()
                    //TODO: replace finish with back action
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
package com.kamal.chatapplication.addroom

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.kamal.chatapplication.base.BaseViewModel
import com.kamal.chatapplication.database.Room
import com.kamal.chatapplication.database.RoomDao

class AddRoomViewModel:BaseViewModel<AddRoomNavigator>() {
    val roomName = ObservableField<String>()
    val roomDesc = ObservableField<String>()
    val roomAdded = MutableLiveData<Boolean>()
    var roomNameError = ObservableField<Boolean>(false)
    var roomDescError = ObservableField<Boolean>(false)
    fun addRoom()
    {
        if(!isValid()) return
        val room = Room(room_name = roomName.get(),room_desc = roomDesc.get())
        RoomDao.insertRoom(room, OnCompleteListener {
            if(it.isSuccessful)
            {
                roomAdded.value = true
            }
            else
            {
                messageLiveData.value = it.exception?.localizedMessage
            }
        })
    }
    fun isValid():Boolean
    {
        var valid = true
        if(roomName.get().isNullOrBlank())
        {
            roomNameError.set(true)
            valid = false
        }
        else
        {
            roomNameError.set(false)
        }
        if(roomDesc.get().isNullOrBlank())
        {
            roomDescError.set(true)
            valid = false
        }
        else
        {
            roomDescError.set(false)
        }
        return valid
    }
}
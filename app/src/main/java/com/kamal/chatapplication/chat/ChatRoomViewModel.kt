package com.kamal.chatapplication.chat

import androidx.databinding.ObservableField
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.Timestamp
import com.kamal.chatapplication.UserCurrentData
import com.kamal.chatapplication.base.BaseViewModel
import com.kamal.chatapplication.database.Message
import com.kamal.chatapplication.database.MessageDao

class ChatRoomViewModel :BaseViewModel<ChatRoomNavigator>(){
    var message = ObservableField<String>()
    var messageError = ObservableField<Boolean>(false)
    var roomId:String?=null
    fun send()
    {
        if(!isValid()) return
        var Msg = Message(messageText = message.get(),
        senderName = UserCurrentData.user?.userName,
        senderId = UserCurrentData.user?.id,
            roomId = roomId,
            time = Timestamp.now() // here I can retrieve messages by date which recently or currently
        )
        MessageDao.sendMessage(Msg, OnSuccessListener {
            message.set("")
        }, OnFailureListener {
            messageLiveData.value = it.localizedMessage
        })
    }
    fun isValid():Boolean
    {
        var valid = true
        if(message.get().isNullOrBlank())
        {
            messageError.set(true)
            valid = false
        }
        else
        {
            messageError.set(false)
        }
        return valid
    }
}
package com.kamal.chatapplication.chat

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.islami.base.BaseFragment
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.Query
import com.kamal.chatapplication.R
import com.kamal.chatapplication.database.Message
import com.kamal.chatapplication.database.MessageDao
import com.kamal.chatapplication.database.Room
import com.kamal.chatapplication.databinding.ActivityChatRoomBinding

class ChatRoomFragment:BaseFragment<ActivityChatRoomBinding,ChatRoomViewModel>(),ChatRoomNavigator {
    lateinit var messagesAdapter: MessagesAdapter
    var room: Room?=null
    val args:ChatRoomFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.chatRoomViewModel = viewModel
        viewModel.navigator = this
//        room = intent.getParcelableExtra("room")
        room = args.room
        viewModel.roomId = room?.room_id //this line for setting room id in the view model
        setUpViews()
        subscribeOnMessages(room?.room_id?:"")
    }

    private fun subscribeOnMessages(roomId: String) {
        MessageDao.getMessageRefrance(roomId)
            .orderBy("time", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    showDialog(message = e.localizedMessage)
                    return@addSnapshotListener
                }
                val addMessages = mutableListOf<Message>()
                for (dc in snapshots!!.documentChanges) {
                    when (dc.type) {
                        DocumentChange.Type.ADDED ->
                        {
                            val newMessage = dc.document.toObject(Message::class.java)
                            //this line means that new data being added in this collection I need to take this data and viewing in the recycler view
                            addMessages.add(newMessage)
                        }
                    }
                }
                messagesAdapter.addMessages(addMessages)
                viewDataBinding.chatRecyclerView.smoothScrollToPosition(messagesAdapter.messageList.size) // I used this function to always scroll down the recycler view when new message is being sent
            }
    }
    private fun setUpViews() {
        messagesAdapter = MessagesAdapter(mutableListOf<Message>())
        viewDataBinding.chatRecyclerView.adapter = messagesAdapter
    }

    override fun intializeViewModel(): ChatRoomViewModel {
        return ViewModelProvider(this).get(ChatRoomViewModel::class.java)
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_chat_room
    }
}
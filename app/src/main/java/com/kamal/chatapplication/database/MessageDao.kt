package com.kamal.chatapplication.database

import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.CollectionReference

class MessageDao {
    companion object
    {
        private val Message_Collection_Ref = "messages"
        fun sendMessage(message: Message,onSuccessListener: OnSuccessListener<Void>,onFailureListener: OnFailureListener)
        {
            var room = MyDatabase.getRoomCollection()
                .document(message.roomId?:"") //in this line I get all the rooms
            var messageDocument = room.collection(Message_Collection_Ref) // here I created new collection called messages to separate the messages of each room and made each room have it's own messages
            var newMessageDocument = messageDocument.document() // here I added new message
            message.messageId = newMessageDocument.id //here I added the message id in the new message id
            newMessageDocument.set(message) // here I set the data of the message which being sent in the param
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener)
        }
        fun getMessageRefrance(roomId:String):CollectionReference
        {
            return MyDatabase.getRoomCollection()
                .document(roomId)
                .collection(Message_Collection_Ref)
        }
    }
}
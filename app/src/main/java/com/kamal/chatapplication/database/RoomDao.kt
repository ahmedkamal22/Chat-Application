package com.kamal.chatapplication.database

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.QuerySnapshot

class RoomDao
{
    companion object
    {
        fun insertRoom(room: Room,onCompleteListener: OnCompleteListener<Void>)
        {
            var roomID = MyDatabase.getRoomCollection().document() //in this line I haven't id for room so I will take it from document
            room.room_id = roomID.id
            roomID.set(room)
                .addOnCompleteListener(onCompleteListener)
        }
        fun getRoomsList(addOnSuccessListener: OnSuccessListener<QuerySnapshot>,onFailureListener: OnFailureListener)
        {
            MyDatabase.getRoomCollection()
                .get()
                .addOnSuccessListener(addOnSuccessListener)
                .addOnFailureListener(onFailureListener)

        }
    }
}
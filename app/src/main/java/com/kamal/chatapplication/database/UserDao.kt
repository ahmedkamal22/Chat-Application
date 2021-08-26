package com.kamal.chatapplication.database;

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.DocumentSnapshot

public class UserDao {

    companion object
    {
        fun addUser(user: User,onCompleteListener: OnCompleteListener<Void>)
        {
            MyDatabase.getUserCollection()
                .document(user.id?:"") //for creating new document in db
                .set(user)
                .addOnCompleteListener(onCompleteListener)
        }
        fun getUser(userId:String,onCompleteListener:OnCompleteListener<DocumentSnapshot>)
        {
            MyDatabase.getUserCollection()
                .document(userId)
                .get()
                .addOnCompleteListener(onCompleteListener)
        }
    }
}

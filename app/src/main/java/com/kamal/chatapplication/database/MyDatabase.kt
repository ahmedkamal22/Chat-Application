package com.kamal.chatapplication.database

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

object MyDatabase {
    private val database = FirebaseFirestore.getInstance() //this line return the object of data base
    private val userPath = "user"
    private var roomPath = "room"
    fun getUserCollection():CollectionReference
    {
        return database.collection(userPath)
    }
    fun getRoomCollection():CollectionReference
    {
        return database.collection(roomPath)
    }
}
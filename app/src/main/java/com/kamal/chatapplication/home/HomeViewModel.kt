package com.kamal.chatapplication.home

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.kamal.chatapplication.base.BaseViewModel
import com.kamal.chatapplication.database.Room
import com.kamal.chatapplication.database.RoomDao

class HomeViewModel:BaseViewModel<HomeNavigator>() {
    val roomsLiveData = MutableLiveData<List<Room>>()
  fun getRooms()
  {
      RoomDao.getRoomsList(OnSuccessListener {
          val roomsList:MutableList<Room> = mutableListOf()
            for (document in it) {
                val room = document.toObject(Room::class.java)
                roomsList.add(room)
            }
          roomsLiveData.value = roomsList // for sending rooms list to home
      }, OnFailureListener {
          messageLiveData.value = it.localizedMessage
      })
  }
    fun addRoom()
    {
        navigator?.openRoom()
    }
}
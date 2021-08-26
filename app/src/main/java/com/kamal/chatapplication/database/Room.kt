package com.kamal.chatapplication.database

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize //I used this library here to prevent implementing the abstract functions of the interface parcelable
data class Room(var room_id:String?=null,
                var room_name:String?=null,
                var room_desc:String?=null): Parcelable
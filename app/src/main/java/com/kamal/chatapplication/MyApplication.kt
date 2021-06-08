package com.kamal.chatapplication

import androidx.multidex.MultiDexApplication

//this class executed firstly even if before splach
class MyApplication:MultiDexApplication(){
    override fun onCreate() {
        super.onCreate()
    }
}
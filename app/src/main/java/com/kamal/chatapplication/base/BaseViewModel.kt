package com.kamal.chatapplication.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel<NV>:ViewModel() {
    var navigator:NV?=null
    val messageLiveData = MutableLiveData<String>()
}
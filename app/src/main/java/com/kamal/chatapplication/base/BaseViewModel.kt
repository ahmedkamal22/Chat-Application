package com.kamal.chatapplication.base

import androidx.lifecycle.ViewModel

open class BaseViewModel<NV>:ViewModel() {
    var navigator:NV?=null
}
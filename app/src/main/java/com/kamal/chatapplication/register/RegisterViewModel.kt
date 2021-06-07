package com.kamal.chatapplication.register

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class RegisterViewModel:ViewModel() {
    val name = ObservableField<String>()
    val userName = ObservableField<String>()
    val email = ObservableField<String>()
    val password = ObservableField<String>()
    var nameError = ObservableField<Boolean>(false)
    var userNameError = ObservableField<Boolean>(false)
    var emailError = ObservableField<Boolean>(false)
    var passwordError = ObservableField<Boolean>(false)
    fun register()
    {
        if(!isValid()) return
    }
    fun isValid():Boolean
    {
        var valid = true
        if(name.get().isNullOrBlank())
        {
            nameError.set(true)
            valid = false
        }
        else
        {
            nameError.set(false)
        }
        if(userName.get().isNullOrBlank())
        {
            userNameError.set(true)
            valid = false
        }
        else
        {
            userNameError.set(false)
        }
        if(email.get().isNullOrBlank())
        {
            emailError.set(true)
            valid = false
        }
        else
        {
            emailError.set(false)
        }
        if(password.get().isNullOrBlank() || password.get()?.length?:0 < 8)
        {
            passwordError.set(true)
            valid = false
        }
        else
        {
            passwordError.set(false)
        }
        return valid
    }
}
package com.kamal.chatapplication.login

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.kamal.chatapplication.base.BaseViewModel

class LoginViewModel:BaseViewModel<Navigator>() {
    val email = ObservableField<String>()
    val password = ObservableField<String>()
    var emailError = ObservableField<Boolean>(false)
    var passwordError = ObservableField<Boolean>(false)
    fun login()
    {
        if(!isValid()) return
    }
    fun isValid():Boolean
    {
        var valid = true
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
    fun goToRegister()
    {
        navigator?.goToRegister()
    }
}
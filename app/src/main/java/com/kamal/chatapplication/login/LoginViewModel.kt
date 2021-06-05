package com.kamal.chatapplication.login

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class LoginViewModel:ViewModel() {
    val email = ObservableField<String>()
    val password = ObservableField<String>()
    var emailError = ObservableField<Boolean>(false)
    var passwordError = ObservableField<Boolean>(false)
    fun login()
    {
        if(!isValid())
            return
        Log.e("Email",email.get()?:"")
        Log.e("Password",password.get()?:"")
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
}
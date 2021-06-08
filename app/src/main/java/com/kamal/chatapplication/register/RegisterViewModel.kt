package com.kamal.chatapplication.register

import android.util.Log
import androidx.databinding.ObservableField
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kamal.chatapplication.base.BaseViewModel
import com.kamal.chatapplication.database.User
import com.kamal.chatapplication.database.UserDao

class RegisterViewModel:BaseViewModel<RegisterNavigator>() {
    val name = ObservableField<String>()
    val userName = ObservableField<String>()
    val email = ObservableField<String>()
    val password = ObservableField<String>()
    var nameError = ObservableField<Boolean>(false)
    var userNameError = ObservableField<Boolean>(false)
    var emailError = ObservableField<Boolean>(false)
    var passwordError = ObservableField<Boolean>(false)
    val firebaseAuth = Firebase.auth
    fun register()
    {
        if(!isValid()) return
        firebaseAuth.createUserWithEmailAndPassword(email.get()!!,password.get()!!)
            .addOnCompleteListener(OnCompleteListener {
                task->
                if(task.isSuccessful)
                {
                    val userId = firebaseAuth.currentUser
                    val user = User(userId?.uid,name.get(),userName.get(),email.get())
                    addUserToDB(user)
                }
                else
                {
                    messageLiveData.value = task.exception?.localizedMessage
                }
            })
    }
    fun addUserToDB(user: User)
    {
        UserDao.addUser(user, OnCompleteListener {
            if(it.isSuccessful)
            {
                navigator?.goToHome()
            }
            else
            {
                messageLiveData.value = it.exception?.localizedMessage
            }
        })
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
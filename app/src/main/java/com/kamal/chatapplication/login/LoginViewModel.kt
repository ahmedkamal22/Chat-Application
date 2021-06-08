package com.kamal.chatapplication.login

import android.util.Log
import androidx.databinding.ObservableField
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kamal.chatapplication.base.BaseViewModel
import com.kamal.chatapplication.database.User
import com.kamal.chatapplication.database.UserDao

class LoginViewModel:BaseViewModel<LoginNavigator>() {
    val email = ObservableField<String>()
    val password = ObservableField<String>()
    var emailError = ObservableField<Boolean>(false)
    var passwordError = ObservableField<Boolean>(false)
    val firebaseAuth = Firebase.auth
    fun login()
    {
        if(!isValid()) return
        firebaseAuth.signInWithEmailAndPassword(email.get()!!,password.get()!!)
            .addOnCompleteListener(OnCompleteListener {
                task->
            if(task.isSuccessful)
            {
                getUserData(firebaseAuth.currentUser?.uid?:"")
            }
            else
            {
                messageLiveData.value = task.exception?.localizedMessage
            }
        })
    }
    fun getUserData(userId:String)
    {
        UserDao.getUser(userId, OnCompleteListener {
                snapshot->
            if(snapshot.isSuccessful)
            {
                val currentUser = snapshot.result?.toObject(User::class.java) // this line for getting current user who is retrived from data base
                //to object used for converting data to User type
                Log.e("Current User: ",currentUser?.email?:"")
                navigator?.goToHome()
            }
            else
            {
                messageLiveData.value = snapshot.exception?.localizedMessage
            }
        })
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
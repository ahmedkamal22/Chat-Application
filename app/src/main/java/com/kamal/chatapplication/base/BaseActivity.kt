package com.example.islami.base

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.kamal.chatapplication.base.BaseViewModel

abstract class BaseActivity<DB:ViewDataBinding,VM:BaseViewModel<*>>:AppCompatActivity(){
    //* for any
    lateinit var viewDataBinding: DB
    lateinit var viewModel: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this,getLayoutID())
        viewModel = intializeViewModel()
        viewModel.messageLiveData.observe(this, Observer {
            message->
            showDialog(message = message,posActionName = "ok",
                posAction = DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
        })
    }

    abstract fun intializeViewModel(): VM

    abstract fun getLayoutID(): Int

    fun makeToast(message:String)
    {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
    fun showDialog(title:String?=null,message:String?=null,posActionName:String?=null,
                   posAction:DialogInterface.OnClickListener?=null,negActionName:String?=null,
                   negAction:DialogInterface.OnClickListener?=null,isCancallable:Boolean = true)
    {
        val dialog = AlertDialog.Builder(this)
        dialog.setCancelable(isCancallable)
        dialog.setTitle(title)
        dialog.setMessage(message)
        dialog.setPositiveButton(posActionName,posAction)
        dialog.setNegativeButton(negActionName,negAction)
                .show()
    }
}
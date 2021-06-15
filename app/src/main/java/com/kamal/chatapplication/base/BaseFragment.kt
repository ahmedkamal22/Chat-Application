package com.example.islami.base

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.kamal.chatapplication.base.BaseViewModel

abstract class BaseFragment<DB: ViewDataBinding,VM: BaseViewModel<*>>:Fragment(){
    lateinit var viewDataBinding: DB
    lateinit var viewModel: VM
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater,getLayoutID(),container,false)
        viewModel = intializeViewModel()
        viewModel.messageLiveData.observe(viewLifecycleOwner, Observer {
                message->
            showDialog(message = message,posActionName = "ok",
                posAction = DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
        })
        return viewDataBinding.root
    }
    abstract fun intializeViewModel(): VM

    abstract fun getLayoutID(): Int
    fun makeToast(message:String)
    {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }
    fun showDialog(title:String?=null,message:String?=null,posActionName:String?=null,
                   posAction:DialogInterface.OnClickListener?=null,negActionName:String?=null,
                   negAction:DialogInterface.OnClickListener?=null,isCancallable:Boolean = true)
    {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setCancelable(isCancallable)
        dialog.setTitle(title)
        dialog.setMessage(message)
        dialog.setPositiveButton(posActionName,posAction)
        dialog.setNegativeButton(negActionName,negAction)
                .show()
    }
}
package com.kamal.chatapplication.firebase

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.app.NotificationCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.kamal.chatapplication.R
import com.kamal.chatapplication.UserCurrentData
import com.kamal.chatapplication.chat.ChatRoomActivity
import com.kamal.chatapplication.home.HomeActivity

class MyFirebaseMessagingService:FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token) // this used when I want to send a direct message to this user so this sent to the server
        //so I will save this token in the back-end database no in the local device
        Log.e("Firebase token ",token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        // If I just wrote this function only notifications don't appear so I will show notifications by my self
        if(remoteMessage.notification?.imageUrl == null)
        {
            showNotification(remoteMessage.notification?.title?:"",remoteMessage.notification?.body?:"")
        }
        else
        {
            Glide.with(this)
                .asBitmap()
                .load(remoteMessage.notification?.imageUrl?:"")
                .into(object :CustomTarget<Bitmap>(){
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        showNotification(remoteMessage.notification?.title?:"",remoteMessage.notification?.body?:"",resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                    }
                })
        }
    }
    fun showNotification(title:String,body:String)
    {
        val intent = Intent(this, HomeActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val notificationBuilder = NotificationCompat.Builder(this,
            UserCurrentData.Messages_Notifications_Channel_ID)
        notificationBuilder.setSmallIcon(R.drawable.notification_msg2)
        notificationBuilder.setContentTitle(title)
        notificationBuilder.setContentText(body)
        notificationBuilder.setStyle(NotificationCompat.BigTextStyle().bigText(body))
        notificationBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT)
        notificationBuilder.setContentIntent(pendingIntent)
        notificationBuilder.setAutoCancel(true) //for deleting message
        // For appearing notification
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(10,notificationBuilder.build())
    }
    fun showNotification(title:String,body:String,imageBitmap:Bitmap?)
    {
        val intent = Intent(this, HomeActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val notificationBuilder = NotificationCompat.Builder(this,
            UserCurrentData.Messages_Notifications_Channel_ID)
        notificationBuilder.setSmallIcon(R.drawable.notification_msg2)
        notificationBuilder.setContentTitle(title)
        notificationBuilder.setContentText(body)
        notificationBuilder.setStyle(NotificationCompat.BigTextStyle().bigText(body))
        notificationBuilder.setStyle(NotificationCompat.BigPictureStyle().bigPicture(imageBitmap))
        notificationBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT)
        notificationBuilder.setContentIntent(pendingIntent)
        notificationBuilder.setAutoCancel(true) //for deleting message
        // For appearing notification
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(10,notificationBuilder.build())
    }
}
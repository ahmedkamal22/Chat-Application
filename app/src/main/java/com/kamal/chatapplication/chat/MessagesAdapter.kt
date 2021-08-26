package com.kamal.chatapplication.chat


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kamal.chatapplication.R
import com.kamal.chatapplication.UserCurrentData
import com.kamal.chatapplication.database.Message
import com.kamal.chatapplication.databinding.MessageReceivedBinding
import com.kamal.chatapplication.databinding.MessageSentBinding

class MessagesAdapter(var messageList: MutableList<com.kamal.chatapplication.database.Message>):RecyclerView.Adapter<MessageViewHolder>() {
    val Sent_Message = 0
    val Received_Message = 1
    override fun getItemViewType(position: Int): Int {
        val message = messageList.get(position)
       if(message.senderId.equals(UserCurrentData.user?.id))
       {
           return Sent_Message
       }
        return Received_Message
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        if(viewType == Sent_Message)
        {
            val view:MessageSentBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.message_sent,parent,false
            )
            return SentMessageViewHolder(view)
        }
        val view:MessageReceivedBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.message_received,parent,false
        )
        return ReceivedMessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messageList.get(position)
        holder.bind(message)
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    fun addMessages(addedMessages: MutableList<Message>) {
        val oldMessages = messageList.size // here I get the count of old messages in recycler view before adding data
        messageList.addAll(addedMessages) // I used add all as I want to add more than when element
        notifyItemRangeInserted(oldMessages,addedMessages.size) // to notify the data up to date which being inserted
    }
}
abstract class MessageViewHolder(view:View):RecyclerView.ViewHolder(view)
{
    abstract fun bind(message: com.kamal.chatapplication.database.Message)
}
class SentMessageViewHolder(var itemBinding:MessageSentBinding):MessageViewHolder(itemBinding.root)
{

    override fun bind(message: com.kamal.chatapplication.database.Message) {
        itemBinding.setMessage(message)
        itemBinding.invalidateAll()
    }
}
class ReceivedMessageViewHolder(var itemBinding:MessageReceivedBinding):MessageViewHolder(itemBinding.root)
{
    override fun bind(message: com.kamal.chatapplication.database.Message) {
        itemBinding.setMessage(message)
        itemBinding.invalidateAll()
    }
}
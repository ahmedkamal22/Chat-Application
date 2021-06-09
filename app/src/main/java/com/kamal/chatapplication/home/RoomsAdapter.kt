package com.kamal.chatapplication.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kamal.chatapplication.R
import com.kamal.chatapplication.database.Room
import com.kamal.chatapplication.databinding.LayoutRoomBinding

class RoomsAdapter(var roomsList: List<Room>):RecyclerView.Adapter<RoomsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:LayoutRoomBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
        R.layout.layout_room,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val room = roomsList.get(position)
        holder.bind(room)
    }

    override fun getItemCount(): Int {
        return roomsList.size
    }

    fun changeData(roomsList: List<Room>)
    {
        this.roomsList = roomsList
        notifyDataSetChanged()
    }
    class ViewHolder(val itemBinding:LayoutRoomBinding):RecyclerView.ViewHolder(itemBinding.root)
    {
        fun bind(room: Room)
        {
            itemBinding.roomViewModel = room
        }
    }
}
package com.example.flow.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flow.R
import com.example.flow.data.Item


class ItemAdapter(private val context: Context, private var postList:ArrayList<Item>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val post=postList[position]
        holder.body.text=post.body
    }

    override fun getItemCount(): Int =postList.size

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val body: TextView =itemView.findViewById(R.id.txt)
    }

    fun setPostData(postList: ArrayList<Item>)
    {
        this.postList=postList
        notifyDataSetChanged()
    }
}
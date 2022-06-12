package com.okky.ezhousing.ui.notifications

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.okky.ezhousing.api.response.ListStoryItem
import com.okky.ezhousing.databinding.ItemListLargeBinding
import com.okky.ezhousing.databinding.ItemListSmallBinding

class ListKeranjangAdapter(private val listStory: List<ListStoryItem>) : RecyclerView.Adapter<ListKeranjangAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    class ViewHolder(var binding: ItemListLargeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListLargeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val story = listStory[position]
        holder.binding.tvTanahName.text = story.name
        holder.binding.tvLuas.text = story.description
        Glide.with(holder.itemView.context)
            .load(story.photoUrl)
            .into(holder.binding.imgTanah)

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listStory[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listStory.size

    fun setOnItemClickCallback(onItemClickCallbak: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallbak
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ListStoryItem)
    }
}
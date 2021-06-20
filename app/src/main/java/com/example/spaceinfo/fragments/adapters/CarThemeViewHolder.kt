package com.example.spaceinfo.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceinfo.R
import com.example.spaceinfo.databinding.CardItemBinding
import com.example.spaceinfo.models.ThemeItem
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso

class CarThemeViewHolder( private val binding: CardItemBinding ) :
                        RecyclerView.ViewHolder(binding.root)  {
    fun bind ( item: ThemeItem, listener: OnRecyclerViewItemClickListener ){
        binding.model = item
        binding.root.setOnClickListener{ listener.onItemClick( item ) }
        Picasso.get().load(item.url).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(binding.imageView)
        binding.executePendingBindings()
    }

    companion object{
        fun create(parent: ViewGroup) : CarThemeViewHolder{
            val inflater = LayoutInflater.from(parent.context)
            val binding : CardItemBinding =
                    DataBindingUtil.inflate( inflater, R.layout.card_item, parent,false)
            return CarThemeViewHolder(binding)
        }
    }

}
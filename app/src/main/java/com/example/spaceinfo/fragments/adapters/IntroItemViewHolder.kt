package com.example.spaceinfo.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceinfo.R
import com.example.spaceinfo.databinding.IntroItemLayoutBinding
import com.example.spaceinfo.models.IntroItem

class IntroItemViewHolder ( private  val binding : IntroItemLayoutBinding)
    : RecyclerView.ViewHolder( binding.root ){

    fun bind ( introItem: IntroItem){
        binding.itemModel = introItem
        binding.animationIntro.setAnimation(introItem.mediaRaw)
        binding.executePendingBindings()
    }

    companion object{
        fun create(parent: ViewGroup) : IntroItemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding : IntroItemLayoutBinding =
                    DataBindingUtil.inflate( inflater, R.layout.intro_item_layout, parent,false)
            return IntroItemViewHolder(binding)
        }
    }
}
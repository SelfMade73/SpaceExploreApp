package com.example.spaceinfo.fragments.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceinfo.models.IntroItem

class IntroViewPagerAdapter: RecyclerView.Adapter<IntroItemViewHolder>() {

    var items : List<IntroItem> = emptyList()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroItemViewHolder = IntroItemViewHolder.create(parent)

    override fun getItemCount(): Int  = items.size

    override fun onBindViewHolder(holder: IntroItemViewHolder, position: Int) {
        holder.bind(items[position])
    }
}
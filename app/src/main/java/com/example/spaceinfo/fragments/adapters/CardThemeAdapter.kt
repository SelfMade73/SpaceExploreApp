package com.example.spaceinfo.fragments.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceinfo.models.ThemeItem

class CardThemeAdapter(var listener: OnRecyclerViewItemClickListener)
    : RecyclerView.Adapter<CarThemeViewHolder>() {
    var data : MutableList<ThemeItem> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarThemeViewHolder = CarThemeViewHolder.create(parent)

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CarThemeViewHolder, position: Int) = holder.bind(data[position], listener)

}
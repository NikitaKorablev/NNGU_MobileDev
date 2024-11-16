package com.app_lab6.task7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app_lab6.R

class ViewPagerAdapter internal constructor(private val states: ArrayList<Item>):
    RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task7_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.itemView.run {
        val state: Item = states[position]
        holder.taskView.text = state.task
    }

    override fun getItemCount(): Int {
        return states.size
    }

    class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        val taskView: TextView = view.findViewById(R.id.title)
    }
}
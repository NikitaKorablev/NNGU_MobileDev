package com.lab4.task6

import com.lab4.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ItemAdapter internal constructor(context: Context?, private val states: List<Item>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = inflater.inflate(R.layout.task6_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val state: Item = states[position]

        holder.taskView.text = state.task
        holder.dateView.text = state.date
    }

    override fun getItemCount(): Int {
        return states.size
    }

    class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        val taskView: TextView = view.findViewById(R.id.title)
        val dateView: TextView = view.findViewById(R.id.date)
    }
}
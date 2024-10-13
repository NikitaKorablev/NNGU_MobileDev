package com.lab4.task10

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lab4.R

class ViewPagerAdapter internal constructor(private val data: ArrayList<Item>):
    RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {
    var pos = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task10_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.itemView.run {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = view.findViewById(R.id.text)
        private val numberView: TextView = view.findViewById(R.id.number)

        fun bind(item: Item) {
            numberView.text = item.number.toString()
            textView.text = item.text

            itemView.setOnLongClickListener {
                pos = adapterPosition
//                itemView.showContextMenu()
                false
            }
        }
    }
}
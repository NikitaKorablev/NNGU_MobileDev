package com.app_lab5.task4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.app_lab5.R

class ListViewAdapter(private val items: List<Item>, private val context: Context) : BaseAdapter() {

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Item {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val item = getItem(position)

        // Inflate the layout for each item
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.list_view_item_task4, parent, false)

        // Get the TextView from the layout and set its properties
        view.findViewById<TextView>(R.id.name).text = item.name
        view.findViewById<TextView>(R.id.value).text = item.value

        return view
    }
}

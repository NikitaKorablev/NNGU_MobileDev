package com.app_lab5.task1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
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
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.task1_list_item, parent, false)

        // Get the TextView from the layout and set its properties
        val textView = view.findViewById<TextView>(R.id.itemText)
        textView.text = item.text
        textView.setTextColor(item.textColor)
        view.setBackgroundColor(item.bgColor)

        return view
    }
}

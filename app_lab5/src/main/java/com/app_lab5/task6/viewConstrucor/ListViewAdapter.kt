package com.app_lab5.task6.viewConstrucor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.app_lab5.R
import com.app_lab5.task6.data.Note

class ListViewAdapter(var items: List<Note>, private val context: Context, private val onClick: (Note) -> Unit) : BaseAdapter() {

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Note {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val item = getItem(position)

        // Inflate the layout for each item
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.list_view_item_task6, parent, false)

        // Get the TextView from the layout and set its properties
        view.findViewById<TextView>(R.id.note).text = item.text
        view.findViewById<TextView>(R.id.note2).text = item.email
        view.findViewById<ConstraintLayout>(R.id.layout).setOnClickListener { onClick(item) }

        return view
    }
}

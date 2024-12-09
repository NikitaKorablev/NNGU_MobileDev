package com.app.task4

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.app.R

class SongAdapter(private val context: Context, private val songList: List<Song>) :
    BaseAdapter() {
    private var currentPlayingPosition = -1

    override fun getCount(): Int {
        return songList.size
    }

    override fun getItem(position: Int): Any {
        return songList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_song, parent, false)
        }

        val nameView = convertView?.findViewById<TextView>(R.id.nameView)

        val song = songList[position]
        nameView?.text = song.name
        convertView?.setBackgroundColor(if (position == currentPlayingPosition) Color.YELLOW else Color.TRANSPARENT)

        return convertView
    }

    fun setCurrentPlayingPosition(position: Int) {
        val previousPlayingPosition = currentPlayingPosition
        currentPlayingPosition = position
        if (previousPlayingPosition != -1) {
            notifyDataSetChanged()
        }
        if (currentPlayingPosition != -1) {
            notifyDataSetChanged()
        }
    }
}


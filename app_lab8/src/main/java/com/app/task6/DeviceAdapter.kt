package com.app.task6

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.databinding.BluetoothDeviseBinding

class DeviseAdapter : RecyclerView.Adapter<DeviseAdapter.DeviceViewHolder>() {
    var data: List<Devise> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BluetoothDeviseBinding.inflate(inflater, parent, false)

        return DeviceViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val devise = data[position] // Получение человека из списка данных по позиции

        with(holder.binding) {
            deviseName.text = devise.name
            deviseDistance.text = devise.distance.toString()
        }
    }

    class DeviceViewHolder(val binding: BluetoothDeviseBinding):
        RecyclerView.ViewHolder(binding.root)
}
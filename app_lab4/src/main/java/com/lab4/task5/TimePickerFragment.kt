package com.lab4.task5

import android.app.Dialog
import androidx.fragment.app.DialogFragment
import android.app.TimePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.TimePicker
import android.widget.Toast

class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {
    interface TimeListener {
        fun timeUpdate(hourOfDay: Int, minute: Int)
    }

    private lateinit var listener: TimeListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        listener = activity as TimeListener

        // Use the current time as the default values for the picker.
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        // Create a new instance of TimePickerDialog and return it.
        return TimePickerDialog(requireContext(), this, hour, minute, true)
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        listener.timeUpdate(hourOfDay, minute)
    }
}

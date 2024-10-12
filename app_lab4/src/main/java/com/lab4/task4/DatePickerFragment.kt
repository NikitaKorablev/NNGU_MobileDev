package com.lab4.task4

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import androidx.fragment.app.DialogFragment
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {
    interface DateListener {
        fun dateUpdate(year: Int, month: Int, day: Int)
    }

    private lateinit var listener: DateListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        listener = activity as DateListener

        // Use the current date as the default date in the picker.
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it.
        return DatePickerDialog(requireContext(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        listener.dateUpdate(year, month, day)
    }
}

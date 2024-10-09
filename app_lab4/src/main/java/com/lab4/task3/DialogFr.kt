package com.lab4.task3

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.lab4.R

class DialogFr: DialogFragment() {
    interface OnDataSendListener {
        fun showData(inputText: String)
    }
    private lateinit var view: View

    private lateinit var listener: OnDataSendListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = activity as OnDataSendListener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + "must implement ConfirmationListener.")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        view = layoutInflater.inflate(R.layout.task3_dialog_fragment, null)

        return AlertDialog.Builder(requireContext())
            .setView(view)
            .setMessage("Input some text.")
            .setPositiveButton("Confirm") {_, _ ->
                val inputText: String = view.findViewById<EditText>(R.id.editText).text.toString()
                listener.showData(inputText)
                dismiss()
            }
            .create()
    }
}
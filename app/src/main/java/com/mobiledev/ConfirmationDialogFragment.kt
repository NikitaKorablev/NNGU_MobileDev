package com.mobiledev

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ConfirmationDialogFragment: DialogFragment() {
    interface ConfirmationListener {
        fun confirmButtonClicked()
        var dialogView: View
    }

    private lateinit var listener: ConfirmationListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = activity as ConfirmationListener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + "must implement ConfirmationListener.")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val parent = listener.dialogView.parent as? ViewGroup
        parent?.removeView(listener.dialogView)
        listener.dialogView.findViewById<EditText>(R.id.inputField1).text.clear()
        listener.dialogView.findViewById<EditText>(R.id.inputField2).text.clear()

        return AlertDialog.Builder(requireContext())
            .setView(listener.dialogView)
            .setMessage("Please, confirm the action")
            .setPositiveButton("Confirm") { _, _ ->
                listener.confirmButtonClicked()
                dismiss()
            }
            .create()
    }
}
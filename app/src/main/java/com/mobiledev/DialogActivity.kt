package com.mobiledev

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class DialogActivity: AppCompatActivity(), ConfirmationDialogFragment.ConfirmationListener {
    private lateinit var inputField1: EditText
    private lateinit var inputField2: EditText

    override lateinit var dialogView: View

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_activity)
        dialogView = layoutInflater.inflate(R.layout.dialog_layout, null)

        inputField1 = dialogView.findViewById(R.id.inputField1)
        inputField2 = dialogView.findViewById(R.id.inputField2)

        val buttonPrev = findViewById<Button>(R.id.button_prev)
        val alertButton = findViewById<Button>(R.id.alert_show)

        alertButton.setOnClickListener(this::showAlert)
        buttonPrev.setOnClickListener {
            val intent = Intent(this, BlackSquaresAnimationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showAlert(view: View?) {
        ConfirmationDialogFragment()
            .show(supportFragmentManager, "ConfirmationDDialogTag")
    }

    override fun confirmButtonClicked() {
        val input1 = inputField1.text.toString().toIntOrNull()?: 0
        val input2 = inputField2.text.toString().toIntOrNull()?: 0
        val res = input1 + input2
        Toast.makeText(this, res.toString(), Toast.LENGTH_LONG).show()
        Log.d(TAG, res.toString())
    }

    companion object {
        const val TAG = "DialogActivity"
    }
}
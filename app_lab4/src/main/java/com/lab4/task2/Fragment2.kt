package com.lab4.task2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.lab4.R

class Fragment2: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.task2_fragment2, container, false)

        view.findViewById<Button>(R.id.backButton).setOnClickListener(this::backButtonOnClicked)
        return view
    }

    private fun backButtonOnClicked(view: View?) {
        parentFragmentManager.popBackStack()
    }
}
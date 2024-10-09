package com.lab4.task2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.lab4.R

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.task2_fragment1, container, false)

        view.findViewById<Button>(R.id.pinPage).setOnClickListener(this::addSecondFragment)
        view.findViewById<Button>(R.id.unpinPage).setOnClickListener(this::popSecondFragment)

        return view
    }

    private fun addSecondFragment(view: View?) {
        parentFragmentManager.beginTransaction()
            .hide(this)
            .add(R.id.fragmentContainer, Fragment2())
            .addToBackStack(null)
            .commit()
    }

    private fun popSecondFragment(view: View?) {
        parentFragmentManager.popBackStack()
    }
}
package com.app_lab6.task6

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.widget.TextView

class StopWatch @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var job: Job? = null
    private var stopWatchIsStarted = false

    private var hoursView: TextView? = null
    private var minutesView: TextView? = null
    private var secondsView: TextView? = null

    private var hours: Int = 0
        set(value) {
            field = if (value >= 24) 0 else value
            hoursView?.text = String.format("%02d", field)
            invalidate()
        }

    private var min: Int = 0
        set(value) {
            if (value >= 60) {
                field = 0
                hours += 1
            } else field = value
            minutesView?.text = String.format("%02d", field)
            invalidate()
        }

    private var sec: Int = 0
        set(value) {
            if (value >= 60) {
                field = 0
                min += 1
            } else field = value
            secondsView?.text = String.format("%02d", field)
            invalidate()
        }

    fun start() {
        reset()
        stopWatchIsStarted = true
        job = CoroutineScope(Dispatchers.Main).launch {
            while (stopWatchIsStarted) {
                delay(1000)
                sec += 1
            }
        }
    }

    fun stop() {
        stopWatchIsStarted = false
        job?.cancel()
    }

    private fun reset() {
        hours = 0
        min = 0
        sec = 0
    }

    fun setTextViews(hoursView: TextView, minutesView: TextView, secondsView: TextView) {
        this.hoursView = hoursView
        this.minutesView = minutesView
        this.secondsView = secondsView
        updateTextViews()
    }

    private fun updateTextViews() {
        hoursView?.text = String.format("%02d", hours)
        minutesView?.text = String.format("%02d", min)
        secondsView?.text = String.format("%02d", sec)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        // Вызываем измерение для дочерних элементов
        measureChildren(widthMeasureSpec, heightMeasureSpec)
    }
}

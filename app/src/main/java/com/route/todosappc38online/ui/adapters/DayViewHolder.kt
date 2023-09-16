package com.route.todosappc38online.ui.adapters

import android.view.View
import android.widget.TextView
import com.kizitonwose.calendar.view.ViewContainer
import com.route.todosappc38online.R

class DayViewHolder(val calendarDayView: View) : ViewContainer(calendarDayView) {
    val dayOfMonthText: TextView = calendarDayView.findViewById(R.id.day_of_month_text)
    val dayOfWeekText: TextView = calendarDayView.findViewById(R.id.day_of_week_text)
}
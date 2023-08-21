package com.route.todosappc38online.fragments

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.route.todosappc38online.R
import com.route.todosappc38online.clearTime
import com.route.todosappc38online.database.TodoDatabase
import com.route.todosappc38online.database.model.TodoModel
import java.util.Calendar

class AddTodoBottomSheetFragment : BottomSheetDialogFragment() {
    lateinit var titleEditText: EditText
    lateinit var descriptionEditText: EditText
    lateinit var addTodoButton: Button
    lateinit var timeValueTextView: TextView
    lateinit var timeTextView: TextView
    lateinit var calendar: Calendar
    // Calendar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        calendar = Calendar.getInstance()
        timeTextView = requireView().findViewById(R.id.select_time_text)
        timeValueTextView = requireView().findViewById(R.id.select_time_value)
        titleEditText = requireView().findViewById(R.id.title_edit_text)
        descriptionEditText = requireView().findViewById(R.id.description_edit_text)
        addTodoButton = requireView().findViewById(R.id.add_todo_btn)

        // January -> 0

        // December -> 11
        timeValueTextView.text =
            "${calendar.get(Calendar.DAY_OF_MONTH)} / ${calendar.get(Calendar.MONTH) + 1} / ${
                calendar.get(Calendar.YEAR)
            }"

        timeValueTextView.setOnClickListener {
            showDatePicker()
        }
        addTodoButton.setOnClickListener {
            addTodoToDataBase()
        }
    }

    private fun validateFields(): Boolean {
        //          ""                  //   "               "
        if (titleEditText.text.toString().isEmpty() || titleEditText.text.toString().isBlank()) {
            titleEditText.error = getString(R.string.title_required)

        } else {
            titleEditText.error = null
        }
        if (descriptionEditText.text.toString().isEmpty() || descriptionEditText.text.toString()
                .isBlank()
        ) {
            descriptionEditText.error = "Description Required"
        } else {
            descriptionEditText.error = null
        }


        return true
    }

    private fun addTodoToDataBase() {
        if (validateFields()) {
            val title = titleEditText.text.toString()
            val description = descriptionEditText.text.toString()
            calendar.clearTime()
            TodoDatabase
                .getInstance(requireContext())
                .getTodosDao()
                .insertTodo(
                    TodoModel(
                        title = title,
                        description = description,
                        isDone = false,
                        time = calendar.time,
                    )
                )

            dismiss()
        }
    }

    private fun showDatePicker() {
        val datePicker = DatePickerDialog(requireContext())
        datePicker.show()
        datePicker.setOnDateSetListener { view, year, month, dayOfMonth ->
            timeValueTextView.text = "$dayOfMonth / ${month + 1} / $year"
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            calendar.clearTime()
            // 7 : 56 : 25
        }

    }
}
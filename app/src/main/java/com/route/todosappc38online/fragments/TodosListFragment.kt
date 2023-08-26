package com.route.todosappc38online.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.view.WeekDayBinder
import com.route.todosappc38online.Constant
import com.route.todosappc38online.R
import com.route.todosappc38online.adapters.DayViewHolder
import com.route.todosappc38online.adapters.TodosListAdapter
import com.route.todosappc38online.clearTime
import com.route.todosappc38online.database.TodoDatabase
import com.route.todosappc38online.database.model.Task
import com.route.todosappc38online.databinding.FragmentListBinding
import com.route.todosappc38online.ui.edit_task.EditTaskActivity
import com.zerobranch.layout.SwipeLayout
import com.zerobranch.layout.SwipeLayout.SELECTED_WINDOW_FOCUSED_STATE_SET
import com.zerobranch.layout.SwipeLayout.SwipeActionsListener
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Calendar
import java.util.Date
import java.util.Locale

class TodosListFragment : Fragment() ,SwipeActionsListener , TodosListAdapter.OnTaskClick{

    lateinit var adapter: TodosListAdapter
    lateinit var calendar: Calendar
    lateinit var binding : FragmentListBinding
    var selectedDate: LocalDate? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("NewApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()


        binding.calendarView.dayBinder = object : WeekDayBinder<DayViewHolder> {
            override fun create(view: View): DayViewHolder {
                return DayViewHolder(view)
            }


            override fun bind(container: DayViewHolder, data: WeekDay) {

                container.dayOfWeekText.text = data.date.dayOfWeek.getDisplayName(
                    TextStyle.SHORT,
                    Locale.getDefault())

                container.dayOfMonthText.text = data.date.dayOfMonth.toString()
                val currentSelection = selectedDate

                container.calendarDayView.setOnClickListener {
                    //   20                20
                    if (currentSelection == data.date) {
                        selectedDate = null
                        getTodosByDate(null)
                        binding.calendarView.notifyDateChanged(currentSelection)
                    } else {
                        selectedDate = data.date
                        binding.calendarView.notifyDateChanged(data.date)
                        if (currentSelection != null) {
                            binding.calendarView.notifyDateChanged(currentSelection)
                        }
                    }
                }
                if (selectedDate == data.date) {
                    container.dayOfWeekText.setTextColor(resources.getColor(R.color.blue, null))
                    container.dayOfMonthText.setTextColor(resources.getColor(R.color.blue, null))
                    // Set Date
                    Log.e("TAG", "bind: MONTH (Github Library) = ${selectedDate?.monthValue}")
                    Log.e("TAG", "bind: MONTH (Calendar Class) = ${calendar.get(Calendar.MONTH)}")
                    calendar.set(Calendar.DAY_OF_MONTH, selectedDate?.dayOfMonth!!)
                    calendar.set(Calendar.MONTH, selectedDate?.monthValue?.minus(1)!!)
                    calendar.set(Calendar.YEAR, selectedDate?.year!!)

                    // Clear Time
                    calendar.clearTime()
                    Log.e("TAG", "bind: ${calendar.time.time}")
                    getTodosByDate(calendar.time)
                } else {
                    container.dayOfWeekText.setTextColor(resources.getColor(R.color.black, null))
                    container.dayOfMonthText.setTextColor(resources.getColor(R.color.black, null))

                }
            }


        }
        

        val currentDate = LocalDate.now()
        val currentMonth = YearMonth.now()
        val startDate = currentMonth.minusMonths(100).atStartOfMonth() // Adjust as needed
        val endDate = currentMonth.plusMonths(100).atEndOfMonth()  // Adjust as needed
        val firstDayOfWeek = firstDayOfWeekFromLocale() // Available from the library
        binding.calendarView.setup(startDate, endDate, firstDayOfWeek)
        binding.calendarView.scrollToWeek(currentDate)


    }

    private fun initViews() {
        calendar = Calendar.getInstance()
        adapter = TodosListAdapter(null,this,this)
        binding.todosRecyclerView.adapter = adapter

    }

    override fun onStart() {
        super.onStart()
        loadTasks()
    }
     var items: MutableList<Task>? = null
     fun loadTasks() {
        context?.let {
             items = TodoDatabase.getInstance(it).getTodosDao().getAllTodos()
            adapter.updateData(items)
        }
    }

    fun getTodosByDate(selectedDate: Date?) {
        val todosList = if (selectedDate != null)
            TodoDatabase
                .getInstance(requireContext())
                .getTodosDao()
                .getTodosByDate(selectedDate)
        else
            TodoDatabase
                .getInstance(requireContext())
                .getTodosDao()
                .getAllTodos()
        adapter.updateData(todosList)


    }

    override fun onOpen(direction: Int, isContinuous: Boolean) {
       if(direction == SwipeLayout.RIGHT){
           TodoDatabase.getInstance(requireContext()).getTodosDao().deleteTodo(
               adapter.position?.let { items?.get(it) } ?: Task())
           adapter.position?.let { adapter.notifyItemRemoved(it) }
           loadTasks()
       }
    }

    override fun onClose() {
        adapter.updateData(items)

    }

    override fun onClick(task: Task, position: Int) {
        val intent = Intent(requireContext(), EditTaskActivity::class.java)
        intent.putExtra(Constant.TASK_TITLE, task.title)
        intent.putExtra(Constant.TASK_DETAILS, task.description)
        intent.putExtra(Constant.TASK,task)
        startActivity(intent)
    }


}

package com.route.todosappc38online.fragments

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import com.route.todosappc38online.Constant
import com.route.todosappc38online.R
import com.route.todosappc38online.ui.adapters.TodosListAdapter
import com.route.todosappc38online.data.database.TodoDatabase
import com.route.todosappc38online.data.database.model.Task
import com.route.todosappc38online.databinding.FragmentListBinding
import com.route.todosappc38online.ui.fragments.to_do_list.ToDoListViewModel
import com.route.todosappc38online.ui.edit_task.EditTaskActivity
import com.zerobranch.layout.SwipeLayout
import com.zerobranch.layout.SwipeLayout.SwipeActionsListener
import java.util.Calendar

class TodosListFragment : Fragment() ,SwipeActionsListener , TodosListAdapter.OnTaskClick{

    lateinit var adapter: TodosListAdapter
    lateinit var viewModel: ToDoListViewModel
    lateinit var binding : FragmentListBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this)[ToDoListViewModel::class.java]
        return binding.root
    }

    @SuppressLint("NewApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        adapter.onDoneClick =
            TodosListAdapter.OnDoneClick { task, position ->
                task.isDone = true

                TodoDatabase.getInstance().getTodosDao().updateTodo(task)

                adapter.notifyItemChanged(position)
            }

    }
    val selectedDate = Calendar.getInstance()

    init {
        selectedDate.clear()
    }
    var task : Task = Task()
    private fun initViews() {

        adapter = TodosListAdapter(null,this,this)
        binding.calendarView.selectedDate = CalendarDay.today()
         if (binding.calendarView.isSelected){
             viewModel.loadDateTasks(selectedDate)
         }
        binding.calendarView.setOnDateChangedListener(OnDateSelectedListener { widget, date, selected ->
            if(selected){
                selectedDate.set(Calendar.YEAR,date.year)
                selectedDate.set(Calendar.MONTH,date.month-1) // 3shan fe addToDoFragment el calander Month bybd2 mn  0 ama date  calnderView bybd2 mn 1 3ady fa hn2so 3shan dnya tzbot
                selectedDate.set(Calendar.DAY_OF_MONTH,date.day)
                viewModel.loadDateTasks(selectedDate)
            }
        })

        binding.todosRecyclerView.adapter = adapter
        adapter.onDeleteCLick =
            TodosListAdapter.onDeleteClick { task, position ->
                showDialog()
                this.task = task
            }
        setupLiveData()
    }

    private fun setupLiveData() {

        viewModel.tasks.observe(viewLifecycleOwner){
            adapter.updateData(it?.toMutableList())
        }

    }


    private val myInterfaceForDialog = DialogInterface.OnClickListener { dialog, which ->
        when (which) {
            -1 -> {
                viewModel.deleteTask(task)
                adapter.taskDeleted(task)
            }
            -2 -> dialog.cancel()
            else -> Toast.makeText(requireContext(), "Action Canceled", Toast.LENGTH_LONG).show()
        }
    }
    private fun showDialog(){
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setTitle("Are You Sure?")
        dialogBuilder.setMessage("Delete Task")
        dialogBuilder.setPositiveButton(R.string.yes, myInterfaceForDialog)
        dialogBuilder.setNegativeButton(R.string.cancel, myInterfaceForDialog)

        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadDateTasks(selectedDate)
    }





    override fun onOpen(direction: Int, isContinuous: Boolean) {
        if(direction == SwipeLayout.RIGHT){

        }
    }

    override fun onClose() {


    }

    override fun onClick(task: Task, position: Int) {
        val intent = Intent(requireContext(), EditTaskActivity::class.java)
        intent.putExtra(Constant.TASK_TITLE, task.title)
        intent.putExtra(Constant.TASK_DETAILS, task.description)
        intent.putExtra(Constant.TASK,task)
        startActivity(intent)
    }

    fun loadTaskViewModel() {
        viewModel.loadDateTasks(selectedDate)
    }


}
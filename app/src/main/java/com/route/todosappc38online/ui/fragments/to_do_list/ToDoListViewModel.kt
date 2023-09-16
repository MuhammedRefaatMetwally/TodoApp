package com.route.todosappc38online.ui.fragments.to_do_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.route.todosappc38online.data.database.TodoDatabase
import com.route.todosappc38online.data.database.model.Task
import java.util.Calendar

class ToDoListViewModel : ViewModel() {

    val tasks = MutableLiveData<List<Task>>()

    val itemTitle  = MutableLiveData<String>()
    val itemDescr  = MutableLiveData<String>()

    fun loadDateTasks(date : Calendar) {
            tasks.value = TodoDatabase.getInstance().getTodosDao().getTodosByDate(date.timeInMillis)
    }

    fun deleteTask(task: Task) {
        TodoDatabase.getInstance().getTodosDao().deleteTodo(task)
    }

}
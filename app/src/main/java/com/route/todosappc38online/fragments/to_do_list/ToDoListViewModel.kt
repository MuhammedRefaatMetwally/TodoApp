package com.route.todosappc38online.fragments.to_do_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.route.todosappc38online.database.TodoDatabase
import com.route.todosappc38online.database.model.Task
import java.util.Calendar

class ToDoListViewModel : ViewModel() {

    val tasks = MutableLiveData<List<Task>>()

    fun loadDateTasks(date : Calendar) {
            tasks.value = TodoDatabase.getInstance().getTodosDao().getTodosByDate(date.timeInMillis)
    }

    fun deleteTask(task: Task) {
        TodoDatabase.getInstance().getTodosDao().deleteTodo(task)
    }
}
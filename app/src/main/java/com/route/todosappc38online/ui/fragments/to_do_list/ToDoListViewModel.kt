package com.route.todosappc38online.ui.fragments.to_do_list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.todosappc38online.data.database.TodoDatabase
import com.route.todosappc38online.data.database.model.Task
import com.route.todosappc38online.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class ToDoListViewModel @Inject constructor(private  val repository: TodoRepository) : ViewModel() {

    val tasks = MutableLiveData<List<Task>>()


    fun loadDateTasks(date : Calendar) {

            tasks.value = repository.getAllTaskByDate(date = date.timeInMillis)
    }

     fun deleteTask(task: Task) {
        viewModelScope.launch {
        repository.deleteTask(task)
        }
       // TodoDatabase.getInstance().getTodosDao().deleteTodo(task)
    }

    fun updateTask(task : Task){
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }
}
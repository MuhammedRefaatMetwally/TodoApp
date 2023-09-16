package com.route.todosappc38online.ui.edit_task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.todosappc38online.data.database.model.Task
import com.route.todosappc38online.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel  @Inject constructor(private  val repository: TodoRepository) : ViewModel() {

    fun updateTask(task : Task){
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }
}
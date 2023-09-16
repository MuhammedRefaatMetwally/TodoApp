package com.route.todosappc38online.ui.fragments.addTodo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.todosappc38online.R
import com.route.todosappc38online.clearTime
import com.route.todosappc38online.data.database.TodoDatabase
import com.route.todosappc38online.data.database.model.Task
import com.route.todosappc38online.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AddTodoViewModel @Inject constructor(private  val repository: TodoRepository) : ViewModel() {
    val title  = MutableLiveData<String>()
    val description  = MutableLiveData<String>()
    val date  = MutableLiveData<String>()
    val titleError  = MutableLiveData<String>()
    val descriptionErrro  = MutableLiveData<String>()
    val dateError  = MutableLiveData<String>()
     fun validateFields(): Boolean {

        var isValid = true

        if (title.value.toString().isEmpty() || title.value.toString().isBlank()) {
            titleError.value = "Title is Required"
            isValid = false

        }

        if (description.value.toString().isEmpty() ||description.value.toString().isBlank()
        ) {
            descriptionErrro.value = "Description Required"
            isValid = false
        }

        if (date.value.toString().isEmpty() || date.value.toString().isBlank()) {
            dateError.value = "Date Required"
            isValid = false
        }


        return isValid
    }

     fun addTodoToDataBase(calendar : Calendar , AddToDoClick : () -> Unit) {

        val task = Task(
            title = title.value.toString(),
            description = description.value.toString(),
            date = calendar.timeInMillis
        )
        calendar.clearTime()

        viewModelScope.launch {
            repository.insertTask(task)
        }
        /*TodoDatabase
            .getInstance()
            .getTodosDao()
            .insertTodo(task)*/
       AddToDoClick()
    }
}
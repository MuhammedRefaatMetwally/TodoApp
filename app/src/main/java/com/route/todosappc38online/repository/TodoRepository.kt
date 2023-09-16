package com.route.todosappc38online.repository

import androidx.lifecycle.MutableLiveData
import com.route.todosappc38online.data.database.dao.TodoDao
import com.route.todosappc38online.data.database.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TodoRepository @Inject constructor(private  val todoDao: TodoDao){
    suspend fun insertTask(task : Task) = todoDao.insertTodo(task)
    suspend fun updateTask(task : Task) = todoDao.updateTodo(task)
    suspend fun deleteTask(task : Task) = todoDao.deleteTodo(task)
    fun getAllTaskByDate(date :Long) : List<Task> = todoDao.getTodosByDate(date)
}
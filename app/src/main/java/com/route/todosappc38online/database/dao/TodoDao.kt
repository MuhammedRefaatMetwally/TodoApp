package com.route.todosappc38online.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.route.todosappc38online.database.model.TodoModel
import java.util.Date

//Data Access Object
@Dao
interface TodoDao {
    @Insert
    fun insertTodo(todo: TodoModel)

    @Delete
    fun deleteTodo(todo: TodoModel)

    @Update
    fun updateTodo(todo: TodoModel)

    @Query("SELECT * FROM todo_table")
    fun getAllTodos(): List<TodoModel>

    // Column   Parameter of function

    // 1335085190
    @Query("SELECT * FROM todo_table WHERE time = :time")
    fun getTodosByDate(time: Date): List<TodoModel>
    // 1335085200
}

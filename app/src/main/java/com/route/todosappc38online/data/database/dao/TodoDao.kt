package com.route.todosappc38online.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.route.todosappc38online.data.database.model.Task
import java.util.Date

//Data Access Object
@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Task)

    @Delete
    suspend fun deleteTodo(todo: Task)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTodo(todo: Task)

    @Query("SELECT * FROM todo_table")
     fun getAllTodos(): MutableList<Task>


    @Query("SELECT * FROM todo_table WHERE date = :time")
    fun getTodosByDate(time: Long): List<Task>
}

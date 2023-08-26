package com.route.todosappc38online.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "todo_table")

data class Task(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id: Int? = null,
    @ColumnInfo
    val title: String? = null,
    @ColumnInfo
    val description: String? = null,
    @ColumnInfo
    val isDone: Boolean? = false,
    @ColumnInfo
    val date: Long? = null,


    )
package com.route.todosappc38online.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.route.todosappc38online.database.dao.TodoDao
import com.route.todosappc38online.database.model.TodoModel


@Database(entities = [TodoModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun getTodosDao(): TodoDao


    companion object {
        private val DATABASE_NAME = "Todos-Database"
        private var todoDatabaseInstance: TodoDatabase? = null
        fun getInstance(context: Context): TodoDatabase {
            if (todoDatabaseInstance == null) {
                todoDatabaseInstance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration() // Delete whole data from data base
                    .allowMainThreadQueries()   //X  - Threading - News API

                    // Lost my mobile -> 12 Floor  (3 Days )
                    // Intel Core I7-10750H 8 Core- 16 Threads
                    //Parrarel
                    // 12 Member -> 12 Floor , each one holds 1 floor (5 Hours )
                    .build()
            }
            return todoDatabaseInstance!!
        }

    }
}
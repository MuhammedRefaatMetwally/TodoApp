package com.route.todosappc38online.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.route.todosappc38online.data.database.dao.TodoDao
import com.route.todosappc38online.data.database.model.Task


@Database(entities = [Task::class], version = 1, exportSchema = true) // bya5od el schyema bt3et el data base wa y7fzha fe jason 3shan my7mlhash tany
@TypeConverters(Converters::class)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun getTodosDao(): TodoDao


    companion object {
        private val DATABASE_NAME = "Todos-Database"
        private var instance: TodoDatabase? = null

        fun init(context: Context){
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
        }
        fun getInstance(): TodoDatabase {
            return instance!!
        }

    }
}
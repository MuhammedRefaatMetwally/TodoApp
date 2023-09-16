package com.route.todosappc38online.data.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.route.todosappc38online.data.database.TodoDatabase
import com.route.todosappc38online.data.database.dao.TodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

 @Singleton
 @Provides
 fun provideTodoDao(todoDatabase: TodoDatabase) : TodoDao = todoDatabase.getTodosDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) : TodoDatabase
    = Room.databaseBuilder(
        context.applicationContext,
        TodoDatabase::class.java,
        "Todos-Database"
    ).fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()

}
package com.route.todosappc38online

import android.app.Application
import com.route.todosappc38online.database.TodoDatabase

class TodoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        TodoDatabase.init(this)
    }
}
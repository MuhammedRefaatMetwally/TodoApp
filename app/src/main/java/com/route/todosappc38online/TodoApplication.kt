package com.route.todosappc38online

import android.app.Application
import com.route.isalmic38online.core.local_data_source.AppSharedReferences
import com.route.todosappc38online.data.database.TodoDatabase

class TodoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        TodoDatabase.init(this)
        AppSharedReferences.init(this)
    }
}
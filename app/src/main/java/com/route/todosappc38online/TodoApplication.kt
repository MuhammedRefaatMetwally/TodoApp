package com.route.todosappc38online

import android.app.Application
import com.route.isalmic38online.core.local_data_source.AppSharedReferences
import com.route.todosappc38online.data.database.TodoDatabase
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class TodoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppSharedReferences.init(this)
    }
}
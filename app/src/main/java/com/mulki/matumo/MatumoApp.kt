package com.mulki.matumo

import android.app.Application
import com.mulki.matumo.data.local.AppDatabase
import com.mulki.matumo.data.repository.TaskRepository

class MatumoApp : Application() {

    val database by lazy {

        AppDatabase.getDatabase(this)
    }

    val repository by lazy {

        TaskRepository(
            database.taskDao()
        )
    }
}
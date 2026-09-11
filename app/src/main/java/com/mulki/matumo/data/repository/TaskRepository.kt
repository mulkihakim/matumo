package com.mulki.matumo.data.repository

import com.mulki.matumo.data.local.TaskDao
import com.mulki.matumo.data.local.TaskEntity

class TaskRepository (

    private val taskDao: TaskDao

) {

    suspend fun insertTask(
        task: TaskEntity
    ) {
        taskDao.insertTask(task)
    }

    fun getAllTasks() = taskDao.getAllTasks()

    suspend fun updateTask(
        task: TaskEntity
    ) {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(
        task: TaskEntity
    ) {
        taskDao.deleteTask(task)
    }
}
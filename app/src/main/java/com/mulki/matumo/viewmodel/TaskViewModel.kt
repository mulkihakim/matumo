package com.mulki.matumo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mulki.matumo.data.local.TaskEntity
import com.mulki.matumo.data.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(

    private val repository: TaskRepository

) : ViewModel() {

    val allTasks = repository.getAllTasks()

    fun insertTask(
        task: TaskEntity
    ) {
        viewModelScope.launch {
            repository.insetTask(task)
        }
    }

    fun updateTask(
        task: TaskEntity
    ) {
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }

    fun deleteTask(
        task: TaskEntity
    ) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }
}

class TaskViewModelFactory(

    private val repository: TaskRepository

) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ) : T {

        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")

            return TaskViewModel(repository) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel Class"
        )
    }
}
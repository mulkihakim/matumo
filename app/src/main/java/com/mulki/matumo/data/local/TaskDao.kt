package com.mulki.matumo.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert
    suspend fun inserTask(
        task: TaskEntity
    )

    @Query("SELECT * FROM tasks ORDER BY id DESC")
    fun getALllTasks(): Flow<List<TaskEntity>>

    @Update
    suspend fun updateTask(
        task: TaskEntity
    )

    @Delete
    suspend fun deleteTask(
        task: TaskEntity
    )
}
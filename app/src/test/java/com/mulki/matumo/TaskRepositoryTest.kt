package com.mulki.matumo

import com.mulki.matumo.data.local.TaskDao
import com.mulki.matumo.data.local.TaskEntity
import com.mulki.matumo.data.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Test

class TaskRepositoryTest {

    private val task = TaskEntity(
        judul = "Belajar Kotlin",
        deskripsi = "Membaca materi coroutine",
        tanggalTenggat = "11 Sep 2026",
        kategori = "Biasa"
    )

    @Test
    fun taskEntityUsesExpectedDefaultState() {
        assertEquals(0, task.id)
        assertFalse(task.isSelesai)
        assertNull(task.tanggalSelesai)
    }

    @Test
    fun repositoryForwardsCrudOperationsToDao() = runBlocking {
        val fakeDao = FakeTaskDao()
        val repository = TaskRepository(fakeDao)

        repository.insertTask(task)
        repository.updateTask(task.copy(isSelesai = true))
        repository.deleteTask(task)

        assertEquals(listOf(task), fakeDao.insertedTasks)
        assertEquals(listOf(task.copy(isSelesai = true)), fakeDao.updatedTasks)
        assertEquals(listOf(task), fakeDao.deletedTasks)
        assertEquals(fakeDao.tasks.value, repository.getAllTasks().first())
    }
}

private class FakeTaskDao : TaskDao {

    val tasks = MutableStateFlow<List<TaskEntity>>(emptyList())
    val insertedTasks = mutableListOf<TaskEntity>()
    val updatedTasks = mutableListOf<TaskEntity>()
    val deletedTasks = mutableListOf<TaskEntity>()

    override suspend fun insertTask(task: TaskEntity) {
        insertedTasks += task
        tasks.value = tasks.value + task
    }

    override fun getAllTasks(): Flow<List<TaskEntity>> = tasks

    override suspend fun updateTask(task: TaskEntity) {
        updatedTasks += task
        tasks.value = tasks.value.map { current ->
            if (current.id == task.id) task else current
        }
    }

    override suspend fun deleteTask(task: TaskEntity) {
        deletedTasks += task
        tasks.value = tasks.value - task
    }
}

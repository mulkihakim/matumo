package com.mulki.matumo.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val judul: String,

    val deskripsi: String,

    val tanggalTenggat: String,

    val kategori: String,

    val isSelesai: Boolean = false,

    val tanggalSelesai: String? = null
)
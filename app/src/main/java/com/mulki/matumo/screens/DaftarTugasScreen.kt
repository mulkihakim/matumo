package com.mulki.matumo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mulki.matumo.components.TaskItem
import com.mulki.matumo.ui.theme.*
import com.mulki.matumo.viewmodel.TaskViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaftarTugasScreen (

    navController: NavController,
    viewModel: TaskViewModel
) {

    val tasks by viewModel.allTasks.collectAsState(
        initial = emptyList()
    )

    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Daftar Tugas",
                        style = MaterialTheme.typography.titleMedium
                    )
                },

                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryApp,
                    titleContentColor = Putih,
                    navigationIconContentColor = Putih
                )
            )
        }

    ) { paddingValues ->

        val formatterTanggal = SimpleDateFormat(
            "yyyy-MM-dd",
            Locale.getDefault()
        )

        val hariIni = formatterTanggal.format(
            Date()
        )

        LazyColumn (

            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimary)
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            items(tasks) { task ->

                TaskItem(
                    task = task,
                    onCheckedChange = { checked ->
                        viewModel.updateTask(
                            task.copy(
                                isSelesai = checked,
                                tanggalSelesai =
                                    if (checked) {
                                        if (task.tanggalTenggat < hariIni) {
                                            task.tanggalTenggat
                                        } else {
                                            hariIni
                                        }
                                    } else {
                                        null
                                    }
                            )
                        )
                    },
                    onDeleteClick = {
                        viewModel.deleteTask(task)
                    }
                )
            }
        }
    }
}
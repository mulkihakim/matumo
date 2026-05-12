package com.mulki.matumo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mulki.matumo.data.local.TaskEntity
import com.mulki.matumo.ui.theme.*
import com.mulki.matumo.viewmodel.TaskViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TambahTugasScreen(
    navController: NavController,
    kategori: String,
    viewModel: TaskViewModel
) {

    var isiJudul by remember {
        mutableStateOf("")
    }

    var isiDeskripsi by remember {
        mutableStateOf("")
    }

    val formatterTanggal = SimpleDateFormat(
        "dd MMM yyyy",
        Locale("id", "ID")
    )

    var isiTanggalTenggat by remember {
        mutableStateOf(
            formatterTanggal.format(Date())
        )
    }

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    val warnaUtama =
        if (kategori == "Penting") {
            MerahPenting
        } else {
            HijauBiasa
        }

    val warnaBadge =
        if (kategori == "Penting") {
            BackgroundPenting
        } else {
            BackgroundBiasa
        }

    val datePickerState = rememberDatePickerState()

    if (showDatePicker) {

        DatePickerDialog(

            onDismissRequest = {
                showDatePicker = false
            },

            confirmButton = {

                TextButton(

                    onClick = {

                        val millis =
                            datePickerState.selectedDateMillis

                        if (millis != null) {

                            val formatter =
                                SimpleDateFormat(
                                    "dd MMM yyyy",
                                    Locale("id", "ID")
                                )

                            isiTanggalTenggat =
                                formatter.format(Date(millis))
                        }

                        showDatePicker = false
                    }

                ) {
                    Text("OK")
                }
            }

        ) {

            DatePicker(
                state = datePickerState
            )
        }
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text(
                        text = "Tambah Tugas $kategori",
                        color = Putih,
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
                            contentDescription = "Kembali",
                            tint = Putih
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = warnaUtama
                )
            )
        }

    ) { paddingValues ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp),

            verticalArrangement = Arrangement.Top
        ) {

            Box(

                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .background(warnaBadge)
                    .padding(
                        horizontal = 14.dp,
                        vertical = 6.dp
                    )

            ) {

                Text(
                    text = kategori.uppercase(),
                    color = warnaUtama,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "TANGGAL JATUH TEMPO",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(

                value = isiTanggalTenggat,

                onValueChange = {},

                enabled = false,

                leadingIcon = {

                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Tanggal Tenggat",
                        tint = Color.Gray
                    )
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        showDatePicker = true
                    },

                shape = RoundedCornerShape(12.dp),

                colors = OutlinedTextFieldDefaults.colors(

                    disabledBorderColor = AbuBorder,

                    disabledContainerColor = Putih,

                    disabledTextColor = Color.Black,

                    disabledLeadingIconColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(22.dp))

            Text(
                text = "JUDUL TUGAS",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(

                value = isiJudul,

                onValueChange = {
                    isiJudul = it
                },

                placeholder = {
                    Text(
                        text = "Contoh: Submit laporan",
                        color = AbuText
                    )
                },

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(12.dp),

                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AbuBorder,
                    unfocusedBorderColor = AbuBorder,

                    focusedContainerColor = Putih,
                    unfocusedContainerColor = Putih
                )
            )

            Spacer(modifier = Modifier.height(22.dp))

            Text(
                text = "DESKRIPSI",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(

                value = isiDeskripsi,

                onValueChange = {
                    isiDeskripsi = it
                },

                placeholder = {
                    Text(
                        text = "Jelaskan tugas...",
                        color = AbuText
                    )
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),

                shape = RoundedCornerShape(12.dp),

                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AbuBorder,
                    unfocusedBorderColor = AbuBorder,

                    focusedContainerColor = Putih,
                    unfocusedContainerColor = Putih
                )
            )

            Spacer(modifier = Modifier.height(28.dp))

            Button(

                onClick = {

                    if (
                        isiJudul.isNotBlank() &&
                        isiDeskripsi.isNotBlank()
                    ) {
                        viewModel.insertTask(

                            TaskEntity(

                                judul = isiJudul,
                                deskripsi = isiDeskripsi,
                                tanggalTenggat = isiTanggalTenggat,
                                kategori = kategori,
                                isSelesai = false
                            )
                        )

                        navController.popBackStack()
                    }
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),

                shape = RoundedCornerShape(12.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = warnaUtama
                )

            ) {

                Text(
                    text = "SIMPAN",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
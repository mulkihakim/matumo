package com.mulki.matumo.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mulki.matumo.components.GrafikStatistik
import com.mulki.matumo.components.MenuShortcutCard
import com.mulki.matumo.components.StatistikCard
import com.mulki.matumo.ui.theme.*
import com.mulki.matumo.viewmodel.TaskViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: TaskViewModel
) {

    val tasks by viewModel.allTasks.collectAsState(
        initial = emptyList()
    )

    val tugasSelesai = tasks.count { it.isSelesai }

    val tugasBelumSelesai = tasks.count { !it.isSelesai }

    val tanggalSekarang = SimpleDateFormat(
        "EEEE, dd MMMM yyyy",
        Locale("id", "ID")
    ).format(Date())

    val taskSelesai = tasks.filter {
        it.isSelesai
    }

    val formatterHari = SimpleDateFormat(
        "EEE",
        Locale("id", "ID")
    )

    val formatterFullTanggal = SimpleDateFormat(
        "dd MMM yyyy",
        Locale("id", "ID")
    )

    val calendar = Calendar.getInstance()

    calendar.firstDayOfWeek = Calendar.MONDAY

    calendar.set(
        Calendar.DAY_OF_WEEK,
        Calendar.MONDAY
    )

    data class HariItem(

        val namaHari: String,

        val tanggalAsli: String
    )

    val daftarHariDanTanggal =
        mutableListOf<HariItem>()

    for (i in 0..6) {

        val tempCalendar = calendar.clone() as Calendar

        tempCalendar.add(
            Calendar.DAY_OF_MONTH,
            i
        )

        val namaHari = formatterHari.format(
            tempCalendar.time
        )

        val tanggalAsli = formatterFullTanggal.format(
            tempCalendar.time
        )

        daftarHariDanTanggal.add(
            HariItem(
                namaHari,
                tanggalAsli
            )
        )
    }

    val statistikPerTanggal =
        taskSelesai
            .mapNotNull { task ->

                task.tanggalSelesai
            }
            .groupingBy { it }
            .eachCount()

    val dataGrafik =
        daftarHariDanTanggal.associate { (namaHari, tanggalAsli) ->

            namaHari to (
                    statistikPerTanggal[tanggalAsli] ?: 0
                    )
        }

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Beranda",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryApp,
                    titleContentColor = Putih
                )
            )
        }
    ) {
        paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),

            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Halo, User! 👋",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = tanggalSekarang,
                color = AbuText
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                StatistikCard(
                    title = "TUGAS SELESAI",
                    jumlah = tugasSelesai.toString(),
                    warna = HijauBiasa,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(12.dp))

                StatistikCard(
                    title = "BELUM SELESAI",
                    jumlah = tugasBelumSelesai.toString(),
                    warna = MerahPenting,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(14.dp))

            GrafikStatistik(statistikPerHari = dataGrafik)
          
            Spacer(modifier = Modifier.height(14.dp))

            Column {

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    MenuShortcutCard(
                        title = "Tambah Tugas Penting",
                        icon = Icons.Default.Add,
                        warna = MerahPenting,
                        onClick = {

                            navController.navigate(
                                "tambah_tugas/Penting"
                            )
                        },

                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(14.dp))

                    MenuShortcutCard(
                        title = "Tambah Tugas Biasa",
                        icon = Icons.Default.Add,
                        warna = HijauBiasa,
                        onClick = {
                            navController.navigate(
                                "tambah_tugas/Biasa"
                            )
                        },

                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    MenuShortcutCard(
                        title = "Daftar Tugas",
                        icon = Icons.Default.List,
                        warna = Biru,
                        onClick = {
                            navController.navigate(
                                "daftar_tugas"
                            )
                        },
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(14.dp))

                    MenuShortcutCard(
                        title = "Pengaturan",
                        icon = Icons.Default.Settings,
                        warna = AbuTextGelap,
                        onClick = {
                            navController.navigate(
                                "pengaturan"
                            )
                        },

                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}
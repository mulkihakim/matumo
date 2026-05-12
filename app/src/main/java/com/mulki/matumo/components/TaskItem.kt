package com.mulki.matumo.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Delete
import com.mulki.matumo.data.local.TaskEntity
import com.mulki.matumo.ui.theme.*

@Composable
fun TaskItem(

    task: TaskEntity,
    onCheckedChange: (Boolean) -> Unit,
    onDeleteClick: () -> Unit

) {

    val warnaKategori =
        if (task.kategori == "Penting") {
            MerahPenting
        } else {
            HijauBiasa
        }

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 14.dp),
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(
            1.dp,
            AbuBorder
        ),
        colors = CardDefaults.cardColors(
            containerColor = Putih
        )
    ) {

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Checkbox(
                checked = task.isSelesai,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = warnaKategori,
                    uncheckedColor = AbuText,
                    checkmarkColor = Putih
                )
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = task.judul,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    style = TextStyle(

                        textDecoration =
                            if (task.isSelesai) {
                                TextDecoration.LineThrough
                            } else {
                                TextDecoration.None
                            }
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "${task.tanggalTenggat} . ${task.kategori}",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }

            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Kategori",
                tint = warnaKategori
            )

            if (task.isSelesai) {
                IconButton(
                    onClick = onDeleteClick
                ) {
                   Icon(
                       imageVector = Icons.Default.Delete,
                       contentDescription = "Hapus",
                       tint = Color.Gray
                   )
                }
            }
        }
    }
}
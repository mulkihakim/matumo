package com.mulki.matumo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mulki.matumo.ui.theme.*

@Composable
fun GrafikStatistik(

    statistikPerHari: Map<String, Int>

) {

    val data = statistikPerHari.toList()

    val jumlahMaksimum =
        data.maxOfOrNull { it.second } ?: 1

    Card(

        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(16.dp),

        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            AbuBorder
        ),

        colors = CardDefaults.cardColors(
            containerColor = Putih
        )
    ) {

        Column(

            modifier = Modifier.padding(16.dp)

        ) {

            Text(
                text = "TUGAS SELESAI / HARI",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = AbuTextGelap
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp),

                horizontalArrangement =
                Arrangement.spacedBy(6.dp),

                verticalAlignment =
                Alignment.Bottom

            ) {

                data.forEach {

                        (tanggal, jumlah) ->

                    val tinggiBatang = remember(jumlah) {

                        val maxHeigt = 120f

                        ((jumlah.toFloat() / jumlahMaksimum)
                                * maxHeigt)
                            .dp
                    }

                    Column(

                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        Text(
                            text = jumlah.toString(),
                            style = MaterialTheme.typography
                                .bodySmall,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Box(

                            modifier = Modifier
                                .width(44.dp)
                                .height(
                                    if (jumlah == 0)
                                        4.dp
                                    else
                                        tinggiBatang
                                )
                                .background(

                                    PrimaryApp,

                                    RoundedCornerShape(8.dp)
                                )
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Text(

                            text = tanggal,

                            color = Color.Gray,

                            style =
                            MaterialTheme.typography
                                .bodySmall
                        )
                    }
                }
            }
        }
    }
}
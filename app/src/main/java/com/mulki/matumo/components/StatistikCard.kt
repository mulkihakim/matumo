package com.mulki.matumo.components

import android.icu.text.CaseMap.Title
import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mulki.matumo.ui.theme.AbuBorder
import com.mulki.matumo.ui.theme.AbuTextGelap
import com.mulki.matumo.ui.theme.Putih

@Composable
fun StatistikCard(

    title: String,
    jumlah: String,
    warna: Color,
    modifier: Modifier = Modifier
) {

    Card(

        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(
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
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = AbuTextGelap,
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = jumlah,
                color = warna,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
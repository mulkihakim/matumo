package com.mulki.matumo.components

import android.graphics.drawable.Icon
import android.icu.text.CaseMap.Title
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mulki.matumo.ui.theme.AbuBorder
import com.mulki.matumo.ui.theme.Putih

@Composable
fun MenuShortcutCard(

    title: String,
    icon: ImageVector,
    warna: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            1.dp,
            AbuBorder
        ),
        colors = CardDefaults.cardColors(
            containerColor = Putih
        )
    ) {

        Column(

            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Card(

                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = warna
                )
            ) {

                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = Putih,
                    modifier = Modifier.padding(14.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
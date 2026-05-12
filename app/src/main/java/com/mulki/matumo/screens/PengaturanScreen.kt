package com.mulki.matumo.screens


import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mulki.matumo.ui.theme.*
import com.mulki.matumo.R
import com.mulki.matumo.data.local.PreferenceManager


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PengaturanScreen(

    navController: NavController
) {

    var passwordLama by remember {
        mutableStateOf("")
    }

    var passwordBaru by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    val preferenceManager = remember {
        PreferenceManager(context)
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text(
                        text = "Pengaturan",
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
                    containerColor = PrimaryApp
                )
            )
        }

    ) { paddingValues ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp)
        ) {

            Text(
                text = "GANTI PASSWORD",
                fontWeight = FontWeight.Bold,
                color = AbuTextGelap
            )

            Spacer(modifier = Modifier.height(12.dp))

            Card(

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
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = "PASSWORD SAAT INI",
                        fontWeight = FontWeight.Bold,
                        color = AbuTextGelap
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = passwordLama,
                        onValueChange = {
                            passwordLama = it
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = "PASSWORD BARU",
                        fontWeight = FontWeight.Bold,
                        color = AbuTextGelap
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = passwordBaru,
                        onValueChange = {
                            passwordBaru = it
                        },

                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(

                        onClick = {

                            val passwordSaatIni = preferenceManager.getPassword()

                            if (passwordLama == passwordSaatIni) {
                                preferenceManager.savePassword(
                                    passwordBaru
                                )

                                Toast.makeText(
                                    context,
                                    "Password berhasil diubah",
                                    Toast.LENGTH_SHORT
                                ).show()

                                passwordLama = ""
                                passwordBaru = ""
                            } else {
                                Toast.makeText(
                                    context,
                                    "Password saat ini salah",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryApp
                        )
                    ) {

                        Text(
                            text = "SIMPAN PASSWORD",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "DEVELOPER",
                fontWeight = FontWeight.Bold,
                color = AbuTextGelap
            )

            Spacer(modifier = Modifier.height(12.dp))

            Card(

                shape = RoundedCornerShape(16.dp),
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

                    Image(
                        painter = painterResource(
                            id = R.drawable.mulki
                        ),
                        contentDescription = "Foto Developer",
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {

                        Text(
                            text = "Mulki Hakim",
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "NIM: 2241720131",
                            color = AbuText
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "DEVELOPER APLIKASI",
                            color = PrimaryApp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }

    }
}
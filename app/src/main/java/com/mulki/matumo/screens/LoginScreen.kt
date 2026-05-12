package com.mulki.matumo.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mulki.matumo.R
import com.mulki.matumo.data.local.PreferenceManager
import com.mulki.matumo.ui.theme.AbuBorder
import com.mulki.matumo.ui.theme.AbuText
import com.mulki.matumo.ui.theme.AbuTextGelap
import com.mulki.matumo.ui.theme.Putih

@Composable
fun LoginScreen(navController: NavController) {

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    val preferenceManager = remember {
        PreferenceManager(context)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.matumologo
            ),
            contentDescription = "Logo Matumo",
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Matumo",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Manajemen Tugasmu di Mobile",
            fontSize = 16.sp,
            color = AbuTextGelap
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "USERNAME",
            color = AbuTextGelap,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
            },
            label = {
                Text(
                    text = "Username",
                    color = AbuText
                )
            },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AbuBorder,
                unfocusedBorderColor = AbuBorder,

                focusedContainerColor = Putih,
                unfocusedContainerColor = Putih
            ),
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "PASSWORD",
            color = AbuTextGelap,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(
                    text = "Password",
                    color = AbuText
                )
            },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AbuBorder,
                unfocusedBorderColor = AbuBorder,

                focusedContainerColor = Putih,
                unfocusedContainerColor = Putih
            ),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (
                    username == "user" &&
                    password == preferenceManager.getPassword()
                ) {
                navController.navigate("home")
                } else {
                    Toast.makeText(
                        context,
                        "Username atau password salah",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "LOGIN",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}
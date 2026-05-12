package com.mulki.matumo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.mulki.matumo.navigation.AppNavigation
import com.mulki.matumo.screens.LoginScreen
import com.mulki.matumo.ui.theme.MatumoTheme
import com.mulki.matumo.viewmodel.TaskViewModel
import com.mulki.matumo.viewmodel.TaskViewModelFactory

class MainActivity : ComponentActivity() {

    private val viewModel: TaskViewModel by viewModels{

        TaskViewModelFactory(

            (application as MatumoApp).repository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MatumoTheme {
                AppNavigation(

                    viewModel = viewModel
                )
            }
        }
    }
}


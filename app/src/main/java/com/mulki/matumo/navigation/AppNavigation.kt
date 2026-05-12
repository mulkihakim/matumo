package com.mulki.matumo.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mulki.matumo.screens.DaftarTugasScreen
import com.mulki.matumo.screens.HomeScreen
import com.mulki.matumo.screens.LoginScreen
import com.mulki.matumo.screens.PengaturanScreen
import com.mulki.matumo.screens.TambahTugasScreen
import com.mulki.matumo.viewmodel.TaskViewModel

@Composable
fun AppNavigation(

    viewModel: TaskViewModel
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login",
    ) {
        composable("login") {
            LoginScreen(navController)
        }

        composable("home") {
            HomeScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(
            route = "tambah_tugas/{kategori}",
            arguments = listOf(
                navArgument("kategori") {
                    type = NavType.StringType
                }
            )
        ) {
            val kategori =
                it.arguments?.getString("kategori") ?: "Biasa"

            TambahTugasScreen(
                navController = navController,
                kategori = kategori,
                viewModel = viewModel
            )
        }

        composable("daftar_tugas") {
            DaftarTugasScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable("pengaturan") {
            PengaturanScreen(navController)
        }

    }
}
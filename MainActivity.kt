package com.example.sikumbangmadu_kader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sikumbangmadu_kader.ui.dashboard.DashboardScreen
import com.example.sikumbangmadu_kader.ui.login.LoginScreen
import com.example.sikumbangmadu_kader.ui.theme.SiKumbangMadu_KaderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SiKumbangMadu_KaderTheme {
                SiKumbangMaduApp()
            }
        }
    }
}

@Composable
fun SiKumbangMaduApp() {
    val navController = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(innerPadding)
        ) {
            // Halaman Login
            composable("login") {
                LoginScreen(
                    onLoginClick = { username, password ->
                        // Navigasi dari Login ke Dashboard saat tombol Masuk diklik
                        navController.navigate("dashboard") {
                            // Hapus halaman login dari backstack agar pengguna tidak kembali ke login saat menekan tombol back
                            popUpTo("login") { inclusive = true }
                        }
                    },
                    onForgotPasswordClick = {
                        // Nanti dapat ditambahkan rute untuk Lupa Kata Sandi
                    }
                )
            }

            // Halaman Dashboard
            composable("dashboard") {
                DashboardScreen(
                    onInputDataAnakClick = {
                        // TODO: Navigasi ke Input Data Anak
                    },
                    onInputTumbuhKembangClick = {
                        // TODO: Navigasi ke Input Data Tumbuh Kembang
                    },
                    onLihatSemuaKunjunganClick = {
                        // TODO: Navigasi ke List Kunjungan
                    }
                )
            }
        }
    }
}

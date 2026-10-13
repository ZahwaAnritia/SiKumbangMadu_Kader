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
import com.example.sikumbangmadu_kader.ui.child.AddChildScreen
import com.example.sikumbangmadu_kader.ui.child.ChildHistoryScreen
import com.example.sikumbangmadu_kader.ui.child.ChildListScreen
import com.example.sikumbangmadu_kader.ui.dashboard.DashboardScreen
import com.example.sikumbangmadu_kader.ui.login.LoginScreen
import com.example.sikumbangmadu_kader.ui.profile.HelpCenterScreen
import com.example.sikumbangmadu_kader.ui.profile.LanguageScreen
import com.example.sikumbangmadu_kader.ui.profile.PersonalDataScreen
import com.example.sikumbangmadu_kader.ui.profile.PrivacyPolicyScreen
import com.example.sikumbangmadu_kader.ui.profile.ProfileScreen
import com.example.sikumbangmadu_kader.ui.profile.SecurityScreen
import com.example.sikumbangmadu_kader.ui.profile.SettingsScreen
import com.example.sikumbangmadu_kader.ui.notification.NotificationScreen
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
            // 1. Halaman Login
            composable("login") {
                LoginScreen(
                    onLoginClick = { username, password ->
                        navController.navigate("dashboard") {
                            popUpTo("login") { inclusive = true }
                        }
                    },
                    onForgotPasswordClick = {
                        // TODO: Navigasi Lupa Kata Sandi
                    }
                )
            }

            // 2. Halaman Dashboard (Beranda)
            composable("dashboard") {
                DashboardScreen(
                    onInputDataAnakClick = {
                        navController.navigate("add_child")
                    },
                    onInputTumbuhKembangClick = {
                        // TODO: Navigasi ke Input Data Tumbuh Kembang
                    },
                    onLihatSemuaKunjunganClick = {
                        navController.navigate("child_list")
                    },
                    onNotificationClick = {
                        navController.navigate("notification")
                    },
                    onNavigateToTab = { tabIndex ->
                        when (tabIndex) {
                            1 -> navController.navigate("child_list")
                            3 -> navController.navigate("profile")
                        }
                    }
                )
            }

            // 3. Halaman Daftar Data Anak
            composable("child_list") {
                ChildListScreen(
                    onNavigateToTab = { tabIndex ->
                        when (tabIndex) {
                            0 -> navController.navigate("dashboard") {
                                popUpTo("dashboard") { inclusive = true }
                            }
                            3 -> navController.navigate("profile")
                        }
                    },
                    onAddChildClick = {
                        navController.navigate("add_child")
                    },
                    onChildItemClick = { childModel ->
                        navController.navigate("child_history")
                    }
                )
            }

            // 4. Halaman Riwayat Pemeriksaan Anak
            composable("child_history") {
                ChildHistoryScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onAddExamClick = {
                        navController.navigate("add_child")
                    }
                )
            }

            // 5. Halaman Tambah Data Anak
            composable("add_child") {
                AddChildScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onSaveClick = { childData ->
                        // TODO: Simpan data ke ViewModel / Repository
                        navController.popBackStack()
                    }
                )
            }

            // 6. Halaman Profil
            composable("profile") {
                ProfileScreen(
                    onNavigateToTab = { tabIndex ->
                        when (tabIndex) {
                            0 -> navController.navigate("dashboard") {
                                popUpTo("dashboard") { inclusive = true }
                            }
                            1 -> navController.navigate("child_list")
                        }
                    },
                    onDataPribadiClick = {
                        navController.navigate("personal_data")
                    },
                    onBahasaClick = {
                        navController.navigate("language")
                    },
                    onKeamananAkunClick = {
                        navController.navigate("security")
                    },
                    onPengaturanClick = {
                        navController.navigate("settings")
                    },
                    onPusatBantuanClick = {
                        navController.navigate("help_center")
                    },
                    onKebijakanPrivasiClick = {
                        navController.navigate("privacy_policy")
                    },
                    onLogoutClick = {
                        navController.navigate("login") {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }

            // 7. Halaman Data Pribadi Kader
            composable("personal_data") {
                PersonalDataScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onSaveClick = {
                        navController.popBackStack()
                    }
                )
            }

            // 8. Halaman Pengaturan Bahasa
            composable("language") {
                LanguageScreen(
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }

            // 9. Halaman Keamanan Akun
            composable("security") {
                SecurityScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onChangePasswordClick = {
                        // TODO: Dialog / Screen Ubah Kata Sandi
                    },
                    onActiveDevicesClick = {
                        // TODO: Perangkat Aktif
                    },
                    onDeleteAccountClick = {
                        // TODO: Konfirmasi Hapus Akun
                    }
                )
            }

            // 10. Halaman Pengaturan Umum
            composable("settings") {
                SettingsScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onThemeClick = {
                        // TODO: Pilihan Tema Aplikasi
                    },
                    onClearCacheClick = {
                        // TODO: Action Bersihkan Cache
                    },
                    onRateAppClick = {
                        // TODO: Action Penilaian Aplikasi
                    }
                )
            }

            // 11. Halaman Pusat Bantuan
            composable("help_center") {
                HelpCenterScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onContactAdminClick = {
                        // TODO: Intent WhatsApp / Telepon Admin Posyandu
                    }
                )
            }

            // 12. Halaman Kebijakan Privasi
            composable("privacy_policy") {
                PrivacyPolicyScreen(
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }

            // 13. Halaman Notifikasi
            composable("notification") {
                NotificationScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onNavigateToTab = { tabIndex ->
                        when (tabIndex) {
                            0 -> navController.navigate("dashboard") {
                                popUpTo("dashboard") { inclusive = true }
                            }
                            1 -> navController.navigate("child_list")
                            3 -> navController.navigate("profile")
                        }
                    }
                )
            }
        }
    }
}
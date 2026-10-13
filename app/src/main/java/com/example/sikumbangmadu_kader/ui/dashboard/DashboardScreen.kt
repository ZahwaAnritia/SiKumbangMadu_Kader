package com.example.sikumbangmadu_kader.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sikumbangmadu_kader.ui.theme.SiKumbangMadu_KaderTheme

// Palet Warna Dashboard SiKumbangMadu
private val DashboardPrimaryGreen = Color(0xFF0D6840)
private val DashboardTosca = Color(0xFF00A878)
private val DashboardLightBg = Color(0xFFF4FAF6)
private val DashboardDarkText = Color(0xFF1E293B)
private val DashboardSoftGray = Color(0xFF64748B)
private val DashboardRedAlert = Color(0xFFEF4444)
private val DashboardRedAlertBg = Color(0xFFFEE2E2)
private val DashboardGreenBadgeBg = Color(0xFFDCFCE7)

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    onInputDataAnakClick: () -> Unit = {},
    onInputTumbuhKembangClick: () -> Unit = {},
    onLihatSemuaKunjunganClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {},
    onNavigateToTab: (Int) -> Unit = {}
) {
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            DashboardBottomBar(
                selectedTab = selectedTab,
                onTabSelected = { tab ->
                    selectedTab = tab
                    onNavigateToTab(tab)
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DashboardLightBg)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // 1. TopBar & Sapaan Header
            DashboardHeader(onNotificationClick = onNotificationClick)

            // 2. Quick Actions Button
            ActionButtonsSection(
                onInputDataAnakClick = onInputDataAnakClick,
                onInputTumbuhKembangClick = onInputTumbuhKembangClick
            )

            // 3. Card Statistik
            StatCardSection()

            // 4. Grafik Kunjungan
            ChartSection()

            // 5. Cross-Check Kunjungan
            CrossCheckSection(onLihatSemuaClick = onLihatSemuaKunjunganClick)

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

// ----------------------------------------------------
// 1. TopBar & Header Sapaan
// ----------------------------------------------------
@Composable
fun DashboardHeader(
    modifier: Modifier = Modifier,
    onNotificationClick: () -> Unit = {}
) {
    Column(modifier = modifier.fillMaxWidth()) {
        // Baris Atas: Profil, Judul, Notifikasi
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Foto Profil Lingkaran
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(DashboardPrimaryGreen.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Foto Profil Kader",
                    tint = DashboardPrimaryGreen,
                    modifier = Modifier.size(26.dp)
                )
            }

            // Teks Judul
            Text(
                text = "Dashboard",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = DashboardPrimaryGreen
            )

            // Icon Lonceng Notifikasi dengan Titik Merah
            Box(contentAlignment = Alignment.TopEnd) {
                IconButton(onClick = onNotificationClick) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notifikasi",
                        tint = DashboardDarkText,
                        modifier = Modifier.size(28.dp)
                    )
                }
                // Titik Merah Notifikasi
                Box(
                    modifier = Modifier
                        .padding(top = 8.dp, end = 8.dp)
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(DashboardRedAlert)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Baris Sapaan
        Text(
            text = "Selamat datang kembali,",
            fontSize = 14.sp,
            color = DashboardSoftGray
        )
        Text(
            text = "Halo, Kader!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = DashboardDarkText
        )
    }
}

// ----------------------------------------------------
// 2. Quick Actions (Tombol Input Utama)
// ----------------------------------------------------
@Composable
fun ActionButtonsSection(
    modifier: Modifier = Modifier,
    onInputDataAnakClick: () -> Unit,
    onInputTumbuhKembangClick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Tombol Kiri: Input Data Anak (Hijau Tua)
        Card(
            modifier = Modifier
                .weight(1f)
                .height(96.dp)
                .clickable { onInputDataAnakClick() },
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = DashboardPrimaryGreen),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(14.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Face,
                        contentDescription = "Icon Input Anak",
                        tint = Color.White,
                        modifier = Modifier.size(22.dp)
                    )
                }
                Text(
                    text = "Input Data Anak",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        // Tombol Kanan: Input Data Tumbuh Kembang (Tosca Bright)
        Card(
            modifier = Modifier
                .weight(1f)
                .height(96.dp)
                .clickable { onInputTumbuhKembangClick() },
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = DashboardTosca),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(14.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ShowChart,
                        contentDescription = "Icon Tumbuh Kembang",
                        tint = Color.White,
                        modifier = Modifier.size(22.dp)
                    )
                }
                Text(
                    text = "Input Data\nTumbuh Kembang",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    lineHeight = 16.sp
                )
            }
        }
    }
}

// ----------------------------------------------------
// 3. Card Statistik Kunjungan
// ----------------------------------------------------
@Composable
fun StatCardSection(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Icon Kalender Kiri Atas
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(DashboardPrimaryGreen.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Icon Kalender",
                        tint = DashboardPrimaryGreen
                    )
                }

                // Badge Percent Kanan Atas
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = DashboardGreenBadgeBg
                ) {
                    Text(
                        text = "+12% bulan ini",
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = DashboardPrimaryGreen
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Total Kunjungan",
                fontSize = 14.sp,
                color = DashboardSoftGray
            )

            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "142",
                    fontSize = 38.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = DashboardDarkText
                )
                Text(
                    text = "Kunjungan",
                    fontSize = 14.sp,
                    color = DashboardSoftGray,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
            }
        }
    }
}

// ----------------------------------------------------
// 4. Grafik Kunjungan Bulanan (Custom Jetpack Compose Canvas/Box)
// ----------------------------------------------------
@Composable
fun ChartSection(modifier: Modifier = Modifier) {
    val months = listOf("JAN", "FEB", "MAR", "APR", "MEI", "JUN")
    val heightsRatio = listOf(0.45f, 0.60f, 0.85f, 0.70f, 0.95f, 0.80f)

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            // Header Grafik
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Text(
                        text = "Monitoring Kunjungan Bulanan",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = DashboardDarkText
                    )
                    Text(
                        text = "Target tercapai 85%",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = DashboardTosca,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }

                // Badge 6 Bulan Terakhir
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = DashboardLightBg
                ) {
                    Text(
                        text = "6 Bulan Terakhir",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontSize = 11.sp,
                        color = DashboardSoftGray
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Batang Grafik
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                months.forEachIndexed { index, month ->
                    val ratio = heightsRatio[index]
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        Box(
                            modifier = Modifier
                                .width(22.dp)
                                .height(100.dp),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            // Track Belakang
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(DashboardLightBg)
                            )
                            // Batang Bergradisi
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(ratio)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(
                                        brush = Brush.verticalGradient(
                                            colors = listOf(
                                                DashboardTosca,
                                                DashboardPrimaryGreen
                                            )
                                        )
                                    )
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = month,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = DashboardSoftGray
                        )
                    }
                }
            }
        }
    }
}

// ----------------------------------------------------
// 5. Cross-Check Kunjungan Section
// ----------------------------------------------------
@Composable
fun CrossCheckSection(
    modifier: Modifier = Modifier,
    onLihatSemuaClick: () -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Cross-check Kunjungan",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = DashboardDarkText
            )
            Text(
                text = "Lihat Semua",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = DashboardPrimaryGreen,
                modifier = Modifier.clickable { onLihatSemuaClick() }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Item 1: Belum Diinput
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(DashboardRedAlertBg),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Cancel,
                        contentDescription = "Status Belum Diinput",
                        tint = DashboardRedAlert,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Arka Putra",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = DashboardDarkText
                    )
                    Text(
                        text = "Belum Diinput",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = DashboardRedAlert
                    )
                }

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Detail",
                    tint = DashboardSoftGray
                )
            }
        }

        // Item 2: Jadwal Hari Ini / Sudah Selesai
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(DashboardGreenBadgeBg),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Status Selesai",
                        tint = DashboardPrimaryGreen,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Siti Aminah",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = DashboardDarkText
                    )
                    Text(
                        text = "Jadwal Hari Ini",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = DashboardPrimaryGreen
                    )
                }

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Detail",
                    tint = DashboardSoftGray
                )
            }
        }
    }
}

// ----------------------------------------------------
// 6. Bottom Navigation Bar (4 Menu)
// ----------------------------------------------------
@Composable
fun DashboardBottomBar(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        val items = listOf(
            Triple(0, "Beranda", Icons.Default.Home),
            Triple(1, "Data Anak", Icons.Default.People),
            Triple(2, "Maps", Icons.Default.LocationOn),
            Triple(3, "Profil", Icons.Default.Person)
        )

        items.forEach { (index, label, icon) ->
            val isSelected = selectedTab == index
            NavigationBarItem(
                selected = isSelected,
                onClick = { onTabSelected(index) },
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = label
                    )
                },
                label = {
                    Text(
                        text = label,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = DashboardPrimaryGreen,
                    selectedTextColor = DashboardPrimaryGreen,
                    indicatorColor = DashboardPrimaryGreen.copy(alpha = 0.15f),
                    unselectedIconColor = DashboardSoftGray,
                    unselectedTextColor = DashboardSoftGray
                )
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview() {
    SiKumbangMadu_KaderTheme {
        DashboardScreen()
    }
}

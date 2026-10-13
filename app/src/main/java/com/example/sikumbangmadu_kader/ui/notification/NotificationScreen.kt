package com.example.sikumbangmadu_kader.ui.notification

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PriorityHigh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sikumbangmadu_kader.ui.theme.SiKumbangMadu_KaderTheme

// Palet Warna Notifikasi
private val NotificationPrimaryGreen = Color(0xFF0D6840)
private val NotificationTosca = Color(0xFF00A878)
private val NotificationLightBg = Color(0xFFF4FAF6)
private val NotificationDarkText = Color(0xFF1E293B)
private val NotificationSoftGray = Color(0xFF64748B)
private val NotificationUnreadDot = Color(0xFF10B981)

private val NotificationRed = Color(0xFFEF4444)
private val NotificationRedBg = Color(0xFFFEE2E2)

private val NotificationOrange = Color(0xFFD97706)
private val NotificationOrangeBg = Color(0xFFFEF3C7)

private val NotificationCalendarGreen = Color(0xFF16A34A)
private val NotificationCalendarGreenBg = Color(0xFFDCFCE7)

private val NotificationToscaBg = Color(0xFFCCFBF1)

/**
 * Model data Notifikasi
 */
data class NotificationItemModel(
    val id: String,
    val category: String,
    val title: String,
    val description: String,
    val time: String,
    val isUnread: Boolean,
    val icon: ImageVector,
    val categoryColor: Color,
    val iconBgColor: Color
)

/**
 * Data Dummy Notifikasi (sesuai spesifikasi)
 */
val sampleNotifications = listOf(
    NotificationItemModel(
        id = "1",
        category = "Jadwal Posyandu",
        title = "Jadwal Posyandu besok di RW 04",
        description = "Pastikan semua perlengkapan antropometri sudah tersedia dan dikalibrasi.",
        time = "10 menit yang lalu",
        isUnread = true,
        icon = Icons.Default.CalendarToday,
        categoryColor = NotificationCalendarGreen,
        iconBgColor = NotificationCalendarGreenBg
    ),
    NotificationItemModel(
        id = "2",
        category = "Peringatan Pemeriksaan",
        title = "Anak Budi Santoso belum melakukan pemeriksaan bulan ini",
        description = "Segera hubungi orang tua untuk penjadwalan ulang kunjungan rumah.",
        time = "1 jam yang lalu",
        isUnread = true,
        icon = Icons.Default.PriorityHigh,
        categoryColor = NotificationRed,
        iconBgColor = NotificationRedBg
    ),
    NotificationItemModel(
        id = "3",
        category = "Update Bidan",
        title = "Update status gizi dari Bidan",
        description = "Laporan bulanan untuk wilayah kerja Anda telah divalidasi oleh Bidan Puskesmas.",
        time = "3 jam yang lalu",
        isUnread = false,
        icon = Icons.AutoMirrored.Filled.TrendingUp,
        categoryColor = NotificationOrange,
        iconBgColor = NotificationOrangeBg
    ),
    NotificationItemModel(
        id = "4",
        category = "Info Sistem",
        title = "Pelatihan Digitalisasi Posyandu",
        description = "Materi pelatihan terbaru untuk penggunaan aplikasi sudah dapat diunduh di menu edukasi.",
        time = "Kemarin, 09:00",
        isUnread = false,
        icon = Icons.Default.Info,
        categoryColor = NotificationTosca,
        iconBgColor = NotificationToscaBg
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(
    onBackClick: () -> Unit = {},
    onNavigateToTab: (Int) -> Unit = {},
    onItemClick: (NotificationItemModel) -> Unit = {}
) {
    var selectedFilter by remember { mutableStateOf("Semua") }
    var selectedTab by remember { mutableIntStateOf(-1) }

    val filterOptions = listOf("Semua", "Penting", "Jadwal", "Gizi")

    // Filter daftar notifikasi sesuai opsi yang dipilih
    val filteredNotifications = remember(selectedFilter) {
        when (selectedFilter) {
            "Penting" -> sampleNotifications.filter { it.category == "Peringatan Pemeriksaan" || it.isUnread }
            "Jadwal" -> sampleNotifications.filter { it.category == "Jadwal Posyandu" }
            "Gizi" -> sampleNotifications.filter { it.category == "Update Bidan" }
            else -> sampleNotifications
        }
    }

    Scaffold(
        topBar = {
            NotificationTopBar(onBackClick = onBackClick)
        },
        bottomBar = {
            NotificationBottomBar(
                selectedTab = selectedTab,
                onTabSelected = { index ->
                    selectedTab = index
                    onNavigateToTab(index)
                }
            )
        },
        containerColor = NotificationLightBg
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // 2. Filter Chips Section
            NotificationFilterSection(
                filterOptions = filterOptions,
                selectedFilter = selectedFilter,
                onFilterSelected = { selectedFilter = it }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 3. Notification List
            NotificationList(
                notifications = filteredNotifications,
                onItemClick = onItemClick
            )
        }
    }
}

/**
 * 1. TopBar (NotificationTopBar)
 * Ikon panah kembali (kiri) dan teks "Notifikasi" (warna hijau tua, tebal).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationTopBar(
    onBackClick: () -> Unit
) {
    Surface(
        shadowElevation = 2.dp,
        color = Color.White
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Notifikasi",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = NotificationPrimaryGreen
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Kembali",
                        tint = NotificationPrimaryGreen
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.White
            )
        )
    }
}

/**
 * 2. Filter Chips (NotificationFilterSection)
 * LazyRow untuk filter: "Semua" (aktif, latar hijau tua, teks putih), "Penting", "Jadwal", "Gizi".
 */
@Composable
fun NotificationFilterSection(
    filterOptions: List<String>,
    selectedFilter: String,
    onFilterSelected: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(filterOptions) { filter ->
            val isSelected = filter == selectedFilter
            val chipBgColor = if (isSelected) NotificationPrimaryGreen else Color(0xFFE2E8F0)
            val chipTextColor = if (isSelected) Color.White else NotificationDarkText

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(chipBgColor)
                    .clickable { onFilterSelected(filter) }
                    .padding(horizontal = 18.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = filter,
                    fontSize = 13.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                    color = chipTextColor
                )
            }
        }
    }
}

/**
 * 3. Daftar Notifikasi (NotificationList menggunakan LazyColumn)
 */
@Composable
fun NotificationList(
    notifications: List<NotificationItemModel>,
    onItemClick: (NotificationItemModel) -> Unit
) {
    if (notifications.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.NotificationsNone,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = NotificationSoftGray.copy(alpha = 0.5f)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Belum ada notifikasi di kategori ini",
                    fontSize = 14.sp,
                    color = NotificationSoftGray,
                    textAlign = TextAlign.Center
                )
            }
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(notifications, key = { it.id }) { item ->
                NotificationItem(
                    item = item,
                    onClick = { onItemClick(item) }
                )
            }
        }
    }
}

/**
 * Komponen Reusable NotificationItem
 * Card putih dengan bayangan/border sangat halus.
 * Struktur Item:
 * Kiri: Ikon dalam lingkaran berwarna.
 * Tengah/Kanan (Kolom): Label kategori (teks kecil berwarna), Judul (bold), Deskripsi (abu-abu), Waktu (abu-abu, kecil).
 * Kanan Atas: Titik kecil (indikator unread) berwarna hijau.
 */
@Composable
fun NotificationItem(
    item: NotificationItemModel,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFEDF2F7)),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Ikon Lingkaran Berwarna
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(item.iconBgColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.category,
                    tint = item.categoryColor,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Kolom Konten Tengah/Kanan
            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Label Kategori (Teks kecil berwarna)
                Text(
                    text = item.category,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = item.categoryColor
                )

                Spacer(modifier = Modifier.height(2.dp))

                // Judul Notifikasi (Bold)
                Text(
                    text = item.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = NotificationDarkText,
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Deskripsi (Abu-abu)
                Text(
                    text = item.description,
                    fontSize = 12.sp,
                    color = NotificationSoftGray,
                    lineHeight = 16.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Waktu (Abu-abu, kecil di bawah)
                Text(
                    text = item.time,
                    fontSize = 11.sp,
                    color = NotificationSoftGray
                )
            }

            // Indikator Belum Dibaca / Unread (Titik kecil berwarna hijau di kanan atas)
            if (item.isUnread) {
                Spacer(modifier = Modifier.width(6.dp))
                Box(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(NotificationUnreadDot)
                )
            }
        }
    }
}

/**
 * 4. Bottom Navigation
 * Tampilkan menu dummy standar: Beranda, Data Anak, Maps, Profil.
 */
@Composable
fun NotificationBottomBar(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    Surface(
        shadowElevation = 8.dp,
        color = Color.White
    ) {
        NavigationBar(
            containerColor = Color.White,
            contentColor = NotificationPrimaryGreen,
            tonalElevation = 0.dp
        ) {
            NavigationBarItem(
                selected = selectedTab == 0,
                onClick = { onTabSelected(0) },
                icon = { Icon(Icons.Default.Home, contentDescription = "Beranda") },
                label = { Text("Beranda", fontSize = 11.sp) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = NotificationPrimaryGreen,
                    selectedTextColor = NotificationPrimaryGreen,
                    indicatorColor = NotificationLightBg,
                    unselectedIconColor = NotificationSoftGray,
                    unselectedTextColor = NotificationSoftGray
                )
            )
            NavigationBarItem(
                selected = selectedTab == 1,
                onClick = { onTabSelected(1) },
                icon = { Icon(Icons.Default.People, contentDescription = "Data Anak") },
                label = { Text("Data Anak", fontSize = 11.sp) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = NotificationPrimaryGreen,
                    selectedTextColor = NotificationPrimaryGreen,
                    indicatorColor = NotificationLightBg,
                    unselectedIconColor = NotificationSoftGray,
                    unselectedTextColor = NotificationSoftGray
                )
            )
            NavigationBarItem(
                selected = selectedTab == 2,
                onClick = { onTabSelected(2) },
                icon = { Icon(Icons.Default.LocationOn, contentDescription = "Maps") },
                label = { Text("Maps", fontSize = 11.sp) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = NotificationPrimaryGreen,
                    selectedTextColor = NotificationPrimaryGreen,
                    indicatorColor = NotificationLightBg,
                    unselectedIconColor = NotificationSoftGray,
                    unselectedTextColor = NotificationSoftGray
                )
            )
            NavigationBarItem(
                selected = selectedTab == 3,
                onClick = { onTabSelected(3) },
                icon = { Icon(Icons.Default.Person, contentDescription = "Profil") },
                label = { Text("Profil", fontSize = 11.sp) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = NotificationPrimaryGreen,
                    selectedTextColor = NotificationPrimaryGreen,
                    indicatorColor = NotificationLightBg,
                    unselectedIconColor = NotificationSoftGray,
                    unselectedTextColor = NotificationSoftGray
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationScreenPreview() {
    SiKumbangMadu_KaderTheme {
        NotificationScreen()
    }
}

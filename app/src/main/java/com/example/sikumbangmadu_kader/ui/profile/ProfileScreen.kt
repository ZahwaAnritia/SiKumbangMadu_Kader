package com.example.sikumbangmadu_kader.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.material3.TextButton
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

// Palet Warna Profil
private val ProfilePrimaryGreen = Color(0xFF0D6840)
private val ProfileTosca = Color(0xFF00A878)
private val ProfileBadgeBg = Color(0xFFE6F4EA)
private val ProfileLightBg = Color(0xFFF4FAF6)
private val ProfileDarkText = Color(0xFF1E293B)
private val ProfileSoftGray = Color(0xFF64748B)
private val ProfileRedAlert = Color(0xFFEF4444)
private val ProfileRedAlertBg = Color(0xFFFEE2E2)

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onNavigateToTab: (Int) -> Unit = {},
    onDataPribadiClick: () -> Unit = {},
    onBahasaClick: () -> Unit = {},
    onKeamananAkunClick: () -> Unit = {},
    onPengaturanClick: () -> Unit = {},
    onPusatBantuanClick: () -> Unit = {},
    onKebijakanPrivasiClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    var selectedTab by remember { mutableIntStateOf(3) } // Tab 3: Profil aktif
    var showLogoutDialog by remember { mutableStateOf(false) }

    // Dialog Konfirmasi Keluar (Logout)
    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            icon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Logout,
                    contentDescription = "Logout",
                    tint = ProfileRedAlert,
                    modifier = Modifier.size(32.dp)
                )
            },
            title = {
                Text(
                    text = "Konfirmasi Keluar",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = ProfileDarkText
                )
            },
            text = {
                Text(
                    text = "Apakah Anda yakin ingin keluar dari akun ini? Anda perlu memasukkan username dan kata sandi kembali untuk masuk.",
                    fontSize = 14.sp,
                    color = ProfileSoftGray
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showLogoutDialog = false
                        onLogoutClick()
                    }
                ) {
                    Text(
                        text = "Keluar",
                        color = ProfileRedAlert,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showLogoutDialog = false }
                ) {
                    Text(
                        text = "Batal",
                        color = ProfilePrimaryGreen,
                        fontWeight = FontWeight.Medium
                    )
                }
            },
            containerColor = Color.White,
            shape = RoundedCornerShape(20.dp)
        )
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            ProfileTopBar()
        },
        bottomBar = {
            ProfileBottomBar(
                selectedTab = selectedTab,
                onTabSelected = {
                    selectedTab = it
                    onNavigateToTab(it)
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ProfileLightBg)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // 2. Info Pengguna
            UserInfoSection()

            // 3. Card Verifikasi
            VerificationBadge()

            // 4. Menu Pengaturan
            MenuSection(
                onDataPribadiClick = onDataPribadiClick,
                onBahasaClick = onBahasaClick,
                onKeamananAkunClick = onKeamananAkunClick,
                onPengaturanClick = onPengaturanClick,
                onPusatBantuanClick = onPusatBantuanClick,
                onKebijakanPrivasiClick = onKebijakanPrivasiClick
            )

            // 5. Tombol Keluar & Versi (Memicu AlertDialog)
            LogoutSection(onLogoutClick = { showLogoutDialog = true })

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

// ----------------------------------------------------
// 1. TopBar (ProfileTopBar)
// ----------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = "Profil Saya",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = ProfilePrimaryGreen
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White
        )
    )
}

// ----------------------------------------------------
// 2. Info Pengguna (UserInfoSection)
// ----------------------------------------------------
@Composable
fun UserInfoSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Foto Profil Bulat dengan Ikon Pensil Edit
        Box(contentAlignment = Alignment.BottomEnd) {
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape)
                    .background(ProfilePrimaryGreen.copy(alpha = 0.15f))
                    .border(2.dp, ProfilePrimaryGreen, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Foto Profil",
                    tint = ProfilePrimaryGreen,
                    modifier = Modifier.size(54.dp)
                )
            }

            // Tombol Edit Pensil Bulat
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(ProfilePrimaryGreen)
                    .clickable { /* Edit Profil */ },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Foto Profil",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Nama & Subtitle
        Text(
            text = "Siti Khadijah",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = ProfileDarkText
        )

        Text(
            text = "Kader Posyandu • Puskesmas Sejahtera",
            fontSize = 13.sp,
            color = ProfileSoftGray,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}

// ----------------------------------------------------
// 3. Card Verifikasi (VerificationBadge)
// ----------------------------------------------------
@Composable
fun VerificationBadge(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { /* Detail Verifikasi */ },
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = ProfileBadgeBg)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon Centang Terverifikasi
            Icon(
                imageVector = Icons.Default.Verified,
                contentDescription = "Terverifikasi",
                tint = ProfilePrimaryGreen,
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Tenaga Kesehatan Terverifikasi",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = ProfilePrimaryGreen
                )
                Text(
                    text = "ID: KDR-2024-0892",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = ProfilePrimaryGreen.copy(alpha = 0.8f)
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Detail",
                tint = ProfilePrimaryGreen
            )
        }
    }
}

// ----------------------------------------------------
// 4. Menu Pengaturan (MenuSection)
// ----------------------------------------------------
@Composable
fun MenuSection(
    onDataPribadiClick: () -> Unit = {},
    onBahasaClick: () -> Unit = {},
    onKeamananAkunClick: () -> Unit = {},
    onPengaturanClick: () -> Unit = {},
    onPusatBantuanClick: () -> Unit = {},
    onKebijakanPrivasiClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Grup PENGATURAN AKUN
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "PENGATURAN AKUN",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = ProfileSoftGray,
                modifier = Modifier.padding(start = 4.dp)
            )

            ProfileMenuItem(
                icon = Icons.Default.Person,
                title = "Data Pribadi",
                onClick = onDataPribadiClick
            )
            ProfileMenuItem(
                icon = Icons.Default.Language,
                title = "Bahasa",
                subtitle = "Indonesia",
                onClick = onBahasaClick
            )
            ProfileMenuItem(
                icon = Icons.Default.Lock,
                title = "Keamanan Akun",
                onClick = onKeamananAkunClick
            )
            ProfileMenuItem(
                icon = Icons.Default.Settings,
                title = "Pengaturan",
                onClick = onPengaturanClick
            )
        }

        // Grup DUKUNGAN & HUKUM
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "DUKUNGAN & HUKUM",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = ProfileSoftGray,
                modifier = Modifier.padding(start = 4.dp)
            )

            ProfileMenuItem(
                icon = Icons.Default.HelpOutline,
                title = "Pusat Bantuan",
                onClick = onPusatBantuanClick
            )
            ProfileMenuItem(
                icon = Icons.Default.Description,
                title = "Kebijakan Privasi",
                onClick = onKebijakanPrivasiClick
            )
        }
    }
}

// Reusable ProfileMenuItem Component
@Composable
fun ProfileMenuItem(
    icon: ImageVector,
    title: String,
    subtitle: String? = null,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ProfilePrimaryGreen.copy(alpha = 0.08f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = ProfilePrimaryGreen,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = ProfileDarkText,
                modifier = Modifier.weight(1f)
            )

            if (subtitle != null) {
                Text(
                    text = subtitle,
                    fontSize = 13.sp,
                    color = ProfileSoftGray,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Buka $title",
                tint = ProfileSoftGray
            )
        }
    }
}

// ----------------------------------------------------
// 5. Tombol Keluar & Versi (LogoutSection)
// ----------------------------------------------------
@Composable
fun LogoutSection(
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Tombol Keluar Akun
        Button(
            onClick = onLogoutClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ProfileRedAlertBg,
                contentColor = ProfileRedAlert
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Logout,
                    contentDescription = "Keluar Akun",
                    tint = ProfileRedAlert,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Keluar Akun",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = ProfileRedAlert
                )
            }
        }

        // Teks Versi Aplikasi
        Text(
            text = "Versi 2.4.1 (Stable Build)",
            fontSize = 12.sp,
            color = ProfileSoftGray,
            textAlign = TextAlign.Center
        )
    }
}

// ----------------------------------------------------
// 6. Bottom Navigation Bar (Profil Aktif)
// ----------------------------------------------------
@Composable
fun ProfileBottomBar(
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
                    selectedIconColor = ProfilePrimaryGreen,
                    selectedTextColor = ProfilePrimaryGreen,
                    indicatorColor = ProfilePrimaryGreen.copy(alpha = 0.15f),
                    unselectedIconColor = ProfileSoftGray,
                    unselectedTextColor = ProfileSoftGray
                )
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    SiKumbangMadu_KaderTheme {
        ProfileScreen()
    }
}

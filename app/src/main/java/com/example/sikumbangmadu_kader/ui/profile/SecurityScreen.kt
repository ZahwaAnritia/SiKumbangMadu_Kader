package com.example.sikumbangmadu_kader.ui.profile

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sikumbangmadu_kader.ui.theme.SiKumbangMadu_KaderTheme

// Palet Warna
private val PrimaryGreen = Color(0xFF0D6840)
private val LightBg = Color(0xFFF4FAF6)
private val DarkText = Color(0xFF1E293B)
private val SoftGrayText = Color(0xFF64748B)
private val RedAlert = Color(0xFFEF4444)
private val RedAlertBg = Color(0xFFFEE2E2)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecurityScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onChangePasswordClick: () -> Unit = {},
    onActiveDevicesClick: () -> Unit = {},
    onDeleteAccountClick: () -> Unit = {}
) {
    // State Toggle Keamanan Lanjutan
    var isBiometricEnabled by remember { mutableStateOf(true) }
    var isTwoFactorEnabled by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            SecurityTopBar(onBackClick = onBackClick)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LightBg)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // 2. Menu Keamanan Dasar
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                SectionHeader("KEAMANAN DASAR")
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        // Item 1: Ubah Kata Sandi
                        SecurityMenuItem(
                            icon = Icons.Default.Lock,
                            title = "Ubah Kata Sandi",
                            subtitle = "Perbarui kata sandi Anda secara berkala",
                            onClick = onChangePasswordClick
                        )

                        HorizontalDivider(
                            color = Color(0xFFF1F5F9),
                            thickness = 1.dp,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                        // Item 2: Perangkat Aktif
                        SecurityMenuItem(
                            icon = Icons.Default.Smartphone,
                            title = "Perangkat Aktif",
                            subtitle = "Kelola perangkat yang terhubung ke akun ini",
                            onClick = onActiveDevicesClick
                        )
                    }
                }
            }

            // 3. Menu Keamanan Lanjutan (Dengan Switch Toggle)
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                SectionHeader("KEAMANAN LANJUTAN")
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        // Item 3: Login Biometrik
                        SecuritySwitchItem(
                            icon = Icons.Default.Fingerprint,
                            title = "Login Biometrik",
                            subtitle = "Gunakan sidik jari atau wajah untuk masuk",
                            isChecked = isBiometricEnabled,
                            onCheckedChange = { isBiometricEnabled = it }
                        )

                        HorizontalDivider(
                            color = Color(0xFFF1F5F9),
                            thickness = 1.dp,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                        // Item 4: Autentikasi Dua Langkah
                        SecuritySwitchItem(
                            icon = Icons.Default.Security,
                            title = "Autentikasi Dua Langkah",
                            subtitle = "Tambahkan lapisan keamanan ekstra",
                            isChecked = isTwoFactorEnabled,
                            onCheckedChange = { isTwoFactorEnabled = it }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 4. Zona Bahaya (Danger Zone)
            DangerZoneSection(onDeleteAccountClick = onDeleteAccountClick)

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// ----------------------------------------------------
// 1. TopBar (SecurityTopBar)
// ----------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecurityTopBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = "Keamanan Akun",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryGreen
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Kembali",
                    tint = PrimaryGreen
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White
        )
    )
}

// ----------------------------------------------------
// Reusable Component: SecurityMenuItem (Dengan Chevron Arrow)
// ----------------------------------------------------
@Composable
fun SecurityMenuItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 18.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(PrimaryGreen.copy(alpha = 0.08f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = PrimaryGreen,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText
            )
            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = SoftGrayText,
                modifier = Modifier.padding(top = 2.dp)
            )
        }

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Buka $title",
            tint = SoftGrayText
        )
    }
}

// ----------------------------------------------------
// Reusable Component: SecuritySwitchItem (Dengan Toggle Switch)
// ----------------------------------------------------
@Composable
fun SecuritySwitchItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(PrimaryGreen.copy(alpha = 0.08f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = PrimaryGreen,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText
            )
            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = SoftGrayText,
                modifier = Modifier.padding(top = 2.dp)
            )
        }

        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = PrimaryGreen,
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color(0xFFCBD5E1)
            )
        )
    }
}

// ----------------------------------------------------
// 4. Zona Bahaya (DangerZoneSection)
// ----------------------------------------------------
@Composable
fun DangerZoneSection(
    onDeleteAccountClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "ZONA BAHAYA",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = RedAlert,
            modifier = Modifier.padding(start = 4.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onDeleteAccountClick() },
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = RedAlertBg),
            border = androidx.compose.foundation.BorderStroke(1.dp, RedAlert.copy(alpha = 0.3f))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(RedAlert.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.DeleteForever,
                        contentDescription = "Hapus Akun",
                        tint = RedAlert,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Text(
                    text = "Hapus Akun",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = RedAlert,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Hapus Akun",
                    tint = RedAlert
                )
            }
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(
        text = title,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = SoftGrayText,
        modifier = Modifier.padding(start = 4.dp)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SecurityScreenPreview() {
    SiKumbangMadu_KaderTheme {
        SecurityScreen()
    }
}

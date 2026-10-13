package com.example.sikumbangmadu_kader.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyPolicyScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            PrivacyPolicyTopBar(onBackClick = onBackClick)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LightBg)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Tanggal Pembaruan
            Text(
                text = "Terakhir diperbarui: 15 Agustus 2026",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = SoftGrayText
            )

            // Card Pembungkus Konten Kebijakan Privasi
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Seksi 1: Pengumpulan Data
                    PolicySection(
                        title = "1. Pengumpulan Data",
                        content = "Aplikasi SiKumbangMadu mengumpulkan data identitas anak, tanggal lahir, nama orang tua/wali, lokasi posyandu, serta data tumbuh kembang balita (seperti berat dan tinggi badan). Data ini diperoleh langsung melalui input resmi oleh Kader Posyandu yang terverifikasi."
                    )

                    // Seksi 2: Penggunaan Data Medis
                    PolicySection(
                        title = "2. Penggunaan Data Medis",
                        content = "Seluruh data kesehatan dan riwayat tumbuh kembang balita yang tersimpan di dalam sistem digunakan secara tertutup untuk pemantauan gizi, cegah stunting, dan pelaporan berkala kepada Puskesmas serta Kementerian Kesehatan Republik Indonesia. Data tidak akan dijual atau dibagikan kepada pihak ketiga di luar kepentingan medis pemerintah."
                    )

                    // Seksi 3: Kerahasiaan Kader
                    PolicySection(
                        title = "3. Kerahasiaan Kader",
                        content = "Setiap Kader Posyandu wajib menjaga kerahasiaan informasi akun, kata sandi, serta data pribadi balita yang terdaftar. Dilarang menyebarluaskan rekam medis anak kepada pihak yang tidak berwenang sesuai regulasi perlindungan data pribadi medis."
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// ----------------------------------------------------
// 1. TopBar (PrivacyPolicyTopBar)
// ----------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyPolicyTopBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = "Kebijakan Privasi",
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
// Reusable Component PolicySection
// ----------------------------------------------------
@Composable
fun PolicySection(
    title: String,
    content: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = title,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryGreen
        )

        Text(
            text = content,
            style = MaterialTheme.typography.bodyMedium,
            color = DarkText.copy(alpha = 0.85f),
            lineHeight = 22.sp
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PrivacyPolicyScreenPreview() {
    SiKumbangMadu_KaderTheme {
        PrivacyPolicyScreen()
    }
}

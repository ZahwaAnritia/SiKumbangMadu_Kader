package com.example.sikumbangmadu_kader.ui.profile

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.filled.HeadsetMic
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sikumbangmadu_kader.ui.theme.SiKumbangMadu_KaderTheme

// Palet Warna
private val PrimaryGreen = Color(0xFF0D6840)
private val ToscaGreen = Color(0xFF00A878)
private val LightBg = Color(0xFFF4FAF6)
private val DarkText = Color(0xFF1E293B)
private val SoftGrayText = Color(0xFF64748B)

data class FaqModel(
    val question: String,
    val answer: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpCenterScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onContactAdminClick: () -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }

    val dummyFaqs = remember {
        listOf(
            FaqModel(
                question = "Bagaimana cara mengubah data anak yang salah input?",
                answer = "Anda dapat membuka menu 'Daftar Data Anak', pilih nama anak yang ingin diubah, lalu tekan ikon pensil/edit di pojok kanan atas untuk memperbarui data."
            ),
            FaqModel(
                question = "Apa yang harus dilakukan jika lupa kata sandi?",
                answer = "Di halaman Login, tekan tombol 'Lupa Kata Sandi?' atau hubungi Admin Puskesmas setempat untuk mengatur ulang kata sandi akun Kader Anda."
            ),
            FaqModel(
                question = "Bagaimana cara melihat grafik pertumbuhan bulan lalu?",
                answer = "Buka menu 'Daftar Data Anak', pilih profil anak, lalu buka halaman 'Riwayat Pemeriksaan'. Grafik pertumbuhan akan otomatis menampilkan tren perkembangan anak."
            )
        )
    }

    val filteredFaqs = dummyFaqs.filter {
        it.question.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            HelpCenterTopBar(onBackClick = onBackClick)
        },
        bottomBar = {
            ContactAdminSection(onContactAdminClick = onContactAdminClick)
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
            // 2. Header & Search Bar
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = "Ada yang bisa kami bantu?",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkText
                )

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Cari topik bantuan...", color = Color.Gray, fontSize = 14.sp) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Pencarian Bantuan",
                            tint = PrimaryGreen
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(20.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryGreen,
                        unfocusedBorderColor = Color(0xFFE2E8F0),
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // 3. Daftar FAQ (Pertanyaan Sering Diajukan)
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "Pertanyaan Sering Diajukan (FAQ)",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = SoftGrayText,
                    modifier = Modifier.padding(start = 4.dp)
                )

                filteredFaqs.forEach { faq ->
                    FaqItem(faq = faq)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// ----------------------------------------------------
// 1. TopBar (HelpCenterTopBar)
// ----------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpCenterTopBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = "Pusat Bantuan",
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
// 3. Reusable FaqItem Component (Expandable)
// ----------------------------------------------------
@Composable
fun FaqItem(
    faq: FaqModel,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = faq.question,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkText,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Buka Jawaban",
                    tint = PrimaryGreen
                )
            }

            AnimatedVisibility(visible = isExpanded) {
                Text(
                    text = faq.answer,
                    fontSize = 13.sp,
                    color = SoftGrayText,
                    lineHeight = 18.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }
    }
}

// ----------------------------------------------------
// 4. Tombol Hubungi Admin Section
// ----------------------------------------------------
@Composable
fun ContactAdminSection(
    onContactAdminClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 8.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 14.dp)
        ) {
            Button(
                onClick = onContactAdminClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ToscaGreen)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.HeadsetMic,
                        contentDescription = "Hubungi Admin",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Hubungi Admin Posyandu",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HelpCenterScreenPreview() {
    SiKumbangMadu_KaderTheme {
        HelpCenterScreen()
    }
}

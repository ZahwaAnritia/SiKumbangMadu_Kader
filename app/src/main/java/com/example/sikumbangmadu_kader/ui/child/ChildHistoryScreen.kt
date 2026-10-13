package com.example.sikumbangmadu_kader.ui.child

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sikumbangmadu_kader.ui.theme.SiKumbangMadu_KaderTheme

// Palet Warna
private val PrimaryGreen = Color(0xFF0D6840)
private val LightBg = Color(0xFFF4FAF6)
private val DarkText = Color(0xFF1E293B)
private val SoftGrayText = Color(0xFF64748B)

private val BadgeGreenBg = Color(0xFFDCFCE7)
private val BadgeGreenText = Color(0xFF0D6840)

private val BadgeBlueBg = Color(0xFFE0F2FE)
private val BadgeBlueText = Color(0xFF0284C7)

private val BadgeRiskBg = Color(0xFFFEE2E2)
private val BadgeRiskText = Color(0xFFEF4444)

private val BadgeWarningText = Color(0xFFD97706)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildHistoryScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onAddExamClick: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            HistoryTopBar(onBackClick = onBackClick)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddExamClick,
                containerColor = PrimaryGreen,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Pemeriksaan Baru",
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LightBg)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // 2. Profil Ringkas Anak
            ChildProfileCard()

            // 3. Grafik Pertumbuhan (Custom Canvas Chart)
            GrowthChartCard()

            // 4. Timeline Pemeriksaan
            TimelineSection()

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

// ----------------------------------------------------
// 1. TopBar (HistoryTopBar)
// ----------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryTopBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = "Riwayat\nPemeriksaan",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryGreen,
                lineHeight = 20.sp,
                textAlign = TextAlign.Center
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
// 2. Profil Ringkas Anak (ChildProfileCard)
// ----------------------------------------------------
@Composable
fun ChildProfileCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Foto Profil Lingkaran
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .background(PrimaryGreen.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = "Foto Anak",
                    tint = PrimaryGreen,
                    modifier = Modifier.size(42.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Nama Anak
            Text(
                text = "Aria Adiningrat",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Pill Umur & Jenis Kelamin
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = BadgeGreenBg
                ) {
                    Text(
                        text = "2 Tahun 4 Bulan",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = BadgeGreenText
                    )
                }

                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = BadgeBlueBg
                ) {
                    Text(
                        text = "Perempuan",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = BadgeBlueText
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider(
                color = Color(0xFFF1F5F9),
                thickness = 1.dp
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Berat Terakhir & Tinggi Terakhir
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        text = "Berat Terakhir",
                        fontSize = 12.sp,
                        color = SoftGrayText
                    )
                    Text(
                        text = "12.5 kg",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkText
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "Tinggi Terakhir",
                        fontSize = 12.sp,
                        color = SoftGrayText
                    )
                    Text(
                        text = "88 cm",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkText
                    )
                }
            }
        }
    }
}

// ----------------------------------------------------
// 3. Grafik Pertumbuhan (GrowthChartCard with Canvas)
// ----------------------------------------------------
@Composable
fun GrowthChartCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            // Header & Legend
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Grafik Pertumbuhan",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkText
                )

                // Legend Status
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(PrimaryGreen)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Berat", fontSize = 11.sp, color = SoftGrayText)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .clip(RoundedCornerShape(2.dp))
                                .background(BadgeBlueBg)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Rentang Normal", fontSize = 11.sp, color = SoftGrayText)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Custom Canvas Chart
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val w = size.width
                    val h = size.height

                    // Data Points (X: 12bln -> 24bln, Y: Nilai)
                    val points = listOf(
                        Offset(0.05f * w, 0.75f * h),
                        Offset(0.27f * w, 0.65f * h),
                        Offset(0.50f * w, 0.55f * h),
                        Offset(0.73f * w, 0.40f * h),
                        Offset(0.95f * w, 0.25f * h)
                    )

                    // 1. Shaded Normal Range Polygon Path (Latar Belakang Biru Muda)
                    val normalAreaPath = Path().apply {
                        moveTo(0.05f * w, 0.90f * h)
                        lineTo(0.27f * w, 0.82f * h)
                        lineTo(0.50f * w, 0.70f * h)
                        lineTo(0.73f * w, 0.55f * h)
                        lineTo(0.95f * w, 0.40f * h)
                        lineTo(0.95f * w, 0.15f * h)
                        lineTo(0.73f * w, 0.25f * h)
                        lineTo(0.50f * w, 0.35f * h)
                        lineTo(0.27f * w, 0.48f * h)
                        lineTo(0.05f * w, 0.60f * h)
                        close()
                    }
                    drawPath(path = normalAreaPath, color = Color(0xFFE0F2FE))

                    // 2. Garis Tren Hijau
                    val linePath = Path().apply {
                        moveTo(points[0].x, points[0].y)
                        for (i in 1 until points.size) {
                            lineTo(points[i].x, points[i].y)
                        }
                    }
                    drawPath(
                        path = linePath,
                        color = PrimaryGreen,
                        style = Stroke(width = 3.dp.toPx())
                    )

                    // 3. Titik-titik Lingkaran di setiap bulan
                    points.forEach { point ->
                        drawCircle(
                            color = Color.White,
                            radius = 6.dp.toPx(),
                            center = point
                        )
                        drawCircle(
                            color = PrimaryGreen,
                            radius = 4.dp.toPx(),
                            center = point
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // X-Axis Labels
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val months = listOf("12bln", "15bln", "18bln", "21bln", "24bln")
                months.forEach { m ->
                    Text(
                        text = m,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = SoftGrayText
                    )
                }
            }
        }
    }
}

// ----------------------------------------------------
// 4. Timeline Pemeriksaan (TimelineSection)
// ----------------------------------------------------
@Composable
fun TimelineSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        // Header Timeline
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 14.dp)
        ) {
            Icon(
                imageVector = Icons.Default.History,
                contentDescription = "Riwayat",
                tint = PrimaryGreen,
                modifier = Modifier.size(22.dp)
            )
            Text(
                text = "Timeline Pemeriksaan",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText
            )
        }

        // Item 1: Pemeriksaan Rutin 28 Bulan (Normal)
        TimelineItem(
            isFirst = true,
            isLast = false,
            badgeText = "Normal",
            badgeColor = BadgeGreenText,
            badgeBgColor = BadgeGreenBg,
            indicatorType = TimelineIndicatorType.NORMAL,
            title = "Pemeriksaan Rutin 28 Bulan",
            dateLocation = "15 Agustus 2025 • Puskesmas Mentari",
            description = "Perkembangan fisik & motorik anak dalam kondisi sangat baik. Berat dan tinggi tumbuh ideal.",
            weight = "12.5 kg",
            height = "88 cm",
            isWeightWarning = false
        )

        // Item 2: Pemeriksaan Berkala 24 Bulan (Risiko Underweight)
        TimelineItem(
            isFirst = false,
            isLast = false,
            badgeText = "Risiko [Underweight]",
            badgeColor = BadgeRiskText,
            badgeBgColor = BadgeRiskBg,
            indicatorType = TimelineIndicatorType.WARNING,
            title = "Pemeriksaan Berkala 24 Bulan",
            dateLocation = "15 Mei 2025 • RS Ibu & Anak Sehat",
            description = "Berat badan sedikit di bawah garis standar KMS. Disarankan penambahan asupan gizi protein.",
            weight = "11.2 kg",
            height = "84 cm",
            isWeightWarning = true
        )

        // Item 3: Vaksinasi & Check-up (Normal)
        TimelineItem(
            isFirst = false,
            isLast = true,
            badgeText = "Normal",
            badgeColor = BadgeGreenText,
            badgeBgColor = BadgeGreenBg,
            indicatorType = TimelineIndicatorType.NORMAL,
            title = "Vaksinasi & Check-up",
            dateLocation = "10 Februari 2025 • Klinik Melati",
            description = "Vaksin lanjutan telah diberikan. Kondisi kesehatan umum stabil dan aktif.",
            weight = "10.5 kg",
            height = "81 cm",
            isWeightWarning = false
        )
    }
}

enum class TimelineIndicatorType { NORMAL, WARNING }

@Composable
fun TimelineItem(
    isFirst: Boolean,
    isLast: Boolean,
    badgeText: String,
    badgeColor: Color,
    badgeBgColor: Color,
    indicatorType: TimelineIndicatorType,
    title: String,
    dateLocation: String,
    description: String,
    weight: String,
    height: String,
    isWeightWarning: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        // Kolom Garis Vertikal & Indikator
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(32.dp)
        ) {
            // Indikator Lingkaran
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(
                        if (indicatorType == TimelineIndicatorType.NORMAL) PrimaryGreen else BadgeWarningText
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (indicatorType == TimelineIndicatorType.WARNING) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Peringatan",
                        tint = Color.White,
                        modifier = Modifier.size(14.dp)
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                    )
                }
            }

            // Garis Penghubung Vertikal
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .fillMaxHeight()
                        .background(PrimaryGreen.copy(alpha = 0.25f))
                )
            }
        }

        Spacer(modifier = Modifier.width(10.dp))

        // Card Detail Pemeriksaan
        Card(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Title & Badge Status
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = title,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkText,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = badgeBgColor
                    ) {
                        Text(
                            text = badgeText,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = badgeColor
                        )
                    }
                }

                // Tanggal & Lokasi
                Text(
                    text = dateLocation,
                    fontSize = 11.sp,
                    color = SoftGrayText,
                    modifier = Modifier.padding(top = 4.dp)
                )

                // Deskripsi Observasi
                Text(
                    text = description,
                    fontSize = 12.sp,
                    color = DarkText.copy(alpha = 0.85f),
                    lineHeight = 16.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Info Berat & Tinggi
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Berat: ", fontSize = 12.sp, color = SoftGrayText)
                        Text(
                            text = weight,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isWeightWarning) BadgeRiskText else DarkText
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Tinggi: ", fontSize = 12.sp, color = SoftGrayText)
                        Text(
                            text = height,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkText
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChildHistoryScreenPreview() {
    SiKumbangMadu_KaderTheme {
        ChildHistoryScreen()
    }
}

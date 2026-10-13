package com.example.sikumbangmadu_kader.ui.child

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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

private val StatusGreenText = Color(0xFF0D6840)
private val StatusGreenBg = Color(0xFFDCFCE7)

private val StatusWarningText = Color(0xFFD97706)
private val StatusWarningBg = Color(0xFFFEF3C7)

private val StatusRiskText = Color(0xFFEF4444)
private val StatusRiskBg = Color(0xFFFEE2E2)

// Model Data Anak Dummy
data class ChildItemModel(
    val id: String,
    val name: String,
    val age: String,
    val status: String,
    val statusColor: Color,
    val statusBgColor: Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildListScreen(
    modifier: Modifier = Modifier,
    onNavigateToTab: (Int) -> Unit = {},
    onAddChildClick: () -> Unit = {},
    onChildItemClick: (ChildItemModel) -> Unit = {}
) {
    var selectedTab by remember { mutableIntStateOf(1) } // Tab 1: Data Anak aktif
    var searchQuery by remember { mutableStateOf("") }

    // Dummy List Data Anak
    val dummyChildrenList = remember {
        listOf(
            ChildItemModel("1", "Arka Dirgantara", "2 Tahun 4 Bulan", "Normal", StatusGreenText, StatusGreenBg),
            ChildItemModel("2", "Zea Humaira", "8 Bulan", "Peringatan", StatusWarningText, StatusWarningBg),
            ChildItemModel("3", "Bima Sakti", "1 Tahun 2 Bulan", "Risiko", StatusRiskText, StatusRiskBg),
            ChildItemModel("4", "Lana Putri", "3 Tahun", "Normal", StatusGreenText, StatusGreenBg)
        )
    }

    // Filter List berdasarkan query pencarian
    val filteredList = dummyChildrenList.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            ChildListTopBar()
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddChildClick,
                containerColor = PrimaryGreen,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Data Anak",
                    modifier = Modifier.size(28.dp)
                )
            }
        },
        bottomBar = {
            ChildListBottomBar(
                selectedTab = selectedTab,
                onTabSelected = {
                    selectedTab = it
                    onNavigateToTab(it)
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(LightBg)
                .padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 2. Header & Search Bar
            item {
                SearchSection(
                    searchQuery = searchQuery,
                    onSearchQueryChange = { searchQuery = it }
                )
            }

            // 3. Ringkasan Status Card
            item {
                SummarySection(
                    normalCount = "12",
                    warningCount = "3",
                    riskCount = "1"
                )
            }

            // Label Daftar
            item {
                Text(
                    text = "Daftar Anak",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkText,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            // 4. List Anak Menggunakan LazyColumn Items
            items(filteredList, key = { it.id }) { child ->
                ChildListItem(
                    child = child,
                    onClick = { onChildItemClick(child) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

// ----------------------------------------------------
// 1. TopBar (ChildListTopBar)
// ----------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildListTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = "Daftar Data Anak",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryGreen
            )
        },
        navigationIcon = {
            Box(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(PrimaryGreen.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profil Kader",
                    tint = PrimaryGreen,
                    modifier = Modifier.size(22.dp)
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White
        )
    )
}

// ----------------------------------------------------
// 2. Header & Search Bar (SearchSection)
// ----------------------------------------------------
@Composable
fun SearchSection(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Daftar Data Anak",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText
        )

        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            placeholder = { Text("Cari nama anak...", color = Color.Gray, fontSize = 14.sp) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Pencarian",
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
}

// ----------------------------------------------------
// 3. Ringkasan Status (SummarySection)
// ----------------------------------------------------
@Composable
fun SummarySection(
    normalCount: String,
    warningCount: String,
    riskCount: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Kotak 1: Normal
        SummaryCard(
            count = normalCount,
            label = "Normal",
            countColor = StatusGreenText,
            modifier = Modifier.weight(1f)
        )

        // Kotak 2: Peringatan
        SummaryCard(
            count = warningCount,
            label = "Peringatan",
            countColor = StatusWarningText,
            modifier = Modifier.weight(1f)
        )

        // Kotak 3: Risiko
        SummaryCard(
            count = riskCount,
            label = "Risiko",
            countColor = StatusRiskText,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun SummaryCard(
    count: String,
    label: String,
    countColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = count,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = countColor
            )
            Text(
                text = label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = SoftGrayText,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}

// ----------------------------------------------------
// 4. Item Daftar Anak (ChildListItem Reusable)
// ----------------------------------------------------
@Composable
fun ChildListItem(
    child: ChildItemModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Foto Profil Anak (Kotak Membulat)
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(PrimaryGreen.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = child.name,
                    tint = PrimaryGreen,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Nama & Umur
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = child.name,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkText
                )
                Text(
                    text = child.age,
                    fontSize = 12.sp,
                    color = SoftGrayText,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            // Badge Status Pill
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = child.statusBgColor
            ) {
                Text(
                    text = child.status,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = child.statusColor
                )
            }

            Spacer(modifier = Modifier.width(6.dp))

            // Chevron Arrow Right
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Detail",
                tint = SoftGrayText
            )
        }
    }
}

// ----------------------------------------------------
// 6. Bottom Navigation Bar (Data Anak Aktif)
// ----------------------------------------------------
@Composable
fun ChildListBottomBar(
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
                    selectedIconColor = PrimaryGreen,
                    selectedTextColor = PrimaryGreen,
                    indicatorColor = PrimaryGreen.copy(alpha = 0.15f),
                    unselectedIconColor = SoftGrayText,
                    unselectedTextColor = SoftGrayText
                )
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChildListScreenPreview() {
    SiKumbangMadu_KaderTheme {
        ChildListScreen()
    }
}

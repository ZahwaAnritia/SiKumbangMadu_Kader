package com.example.sikumbangmadu_kader.ui.child

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Save
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sikumbangmadu_kader.ui.theme.SiKumbangMadu_KaderTheme

// Palet Warna
private val PrimaryGreen = Color(0xFF0D6840)
private val LightGreenBg = Color(0xFFEBF7F1)
private val InfoBannerBg = Color(0xFFE6F4EA)
private val SoftGrayText = Color(0xFF64748B)
private val DarkText = Color(0xFF1E293B)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddChildScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onSaveClick: (ChildData) -> Unit = {}
) {
    // State Form Data Pribadi
    var namaAnak by remember { mutableStateOf("") }
    var jenisKelamin by remember { mutableStateOf("Laki-laki") } // "Laki-laki" atau "Perempuan"
    var tanggalLahir by remember { mutableStateOf("") }
    var namaOrangTua by remember { mutableStateOf("") }
    var noTelpOrangTua by remember { mutableStateOf("") }

    // State Form Lokasi & Posyandu
    var kelurahan by remember { mutableStateOf("Ambarketawang") } // "Ambarketawang" / "Balecatur"
    var dusun by remember { mutableStateOf("") }
    var posyandu by remember { mutableStateOf("") }
    var rt by remember { mutableStateOf("") }
    var rw by remember { mutableStateOf("") }

    Scaffold(
        modifier = modifier,
        topBar = {
            AddChildTopBar(onBackClick = onBackClick)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8FAFC))
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // 2. Info Banner
            InfoBanner()

            // 3. Form Data Pribadi
            PersonalInfoForm(
                namaAnak = namaAnak,
                onNamaAnakChange = { namaAnak = it },
                jenisKelamin = jenisKelamin,
                onJenisKelaminChange = { jenisKelamin = it },
                tanggalLahir = tanggalLahir,
                onTanggalLahirChange = { tanggalLahir = it },
                namaOrangTua = namaOrangTua,
                onNamaOrangTuaChange = { namaOrangTua = it },
                noTelpOrangTua = noTelpOrangTua,
                onNoTelpOrangTuaChange = { noTelpOrangTua = it }
            )

            // 4. Form Lokasi & Posyandu (Dengan Stepper Vertikal)
            LocationForm(
                kelurahan = kelurahan,
                onKelurahanChange = { kelurahan = it },
                dusun = dusun,
                onDusunChange = { dusun = it },
                posyandu = posyandu,
                onPosyanduChange = { posyandu = it },
                rt = rt,
                onRtChange = { rt = it },
                rw = rw,
                onRwChange = { rw = it }
            )

            // 5. Footer & Tombol Simpan
            SaveSection(
                onSaveClick = {
                    onSaveClick(
                        ChildData(
                            namaAnak = namaAnak,
                            jenisKelamin = jenisKelamin,
                            tanggalLahir = tanggalLahir,
                            namaOrangTua = namaOrangTua,
                            noTelpOrangTua = noTelpOrangTua,
                            kelurahan = kelurahan,
                            dusun = dusun,
                            posyandu = posyandu,
                            rt = rt,
                            rw = rw
                        )
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// Data Class penampung data anak
data class ChildData(
    val namaAnak: String,
    val jenisKelamin: String,
    val tanggalLahir: String,
    val namaOrangTua: String,
    val noTelpOrangTua: String,
    val kelurahan: String,
    val dusun: String,
    val posyandu: String,
    val rt: String,
    val rw: String
)

// ----------------------------------------------------
// 1. TopBar (AddChildTopBar)
// ----------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddChildTopBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = "Tambah Data Anak",
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
        actions = {
            IconButton(onClick = { /* Profil */ }) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(PrimaryGreen.copy(alpha = 0.12f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profil Kader",
                        tint = PrimaryGreen,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White
        )
    )
}

// ----------------------------------------------------
// 2. Info Banner
// ----------------------------------------------------
@Composable
fun InfoBanner(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = InfoBannerBg)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Informasi",
                tint = PrimaryGreen,
                modifier = Modifier
                    .size(22.dp)
                    .padding(top = 2.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "Pastikan data yang dimasukkan sesuai dengan Kartu Keluarga atau KIA untuk akurasi pemantauan kesehatan anak.",
                fontSize = 12.sp,
                color = PrimaryGreen,
                lineHeight = 17.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

// ----------------------------------------------------
// 3. Form Data Pribadi
// ----------------------------------------------------
@Composable
fun PersonalInfoForm(
    namaAnak: String,
    onNamaAnakChange: (String) -> Unit,
    jenisKelamin: String,
    onJenisKelaminChange: (String) -> Unit,
    tanggalLahir: String,
    onTanggalLahirChange: (String) -> Unit,
    namaOrangTua: String,
    onNamaOrangTuaChange: (String) -> Unit,
    noTelpOrangTua: String,
    onNoTelpOrangTuaChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(
                text = "Data Pribadi Anak",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText
            )

            // Nama Lengkap Anak
            Column {
                FormLabel(label = "Nama Lengkap Anak")
                OutlinedTextField(
                    value = namaAnak,
                    onValueChange = onNamaAnakChange,
                    placeholder = { Text("Contoh: Andi Wijaya", color = Color.Gray, fontSize = 14.sp) },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = customTextFieldColors(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Jenis Kelamin (Pill Choice)
            Column {
                FormLabel(label = "Jenis Kelamin")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    val options = listOf("Laki-laki", "Perempuan")
                    options.forEach { option ->
                        val isSelected = option == jenisKelamin
                        Surface(
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp)
                                .clickable { onJenisKelaminChange(option) },
                            shape = RoundedCornerShape(22.dp),
                            color = if (isSelected) PrimaryGreen else LightGreenBg,
                            border = if (isSelected) null else borderStrokeDefault()
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = option,
                                    fontSize = 14.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    color = if (isSelected) Color.White else DarkText
                                )
                            }
                        }
                    }
                }
            }

            // Tanggal Lahir
            Column {
                FormLabel(label = "Tanggal Lahir")
                OutlinedTextField(
                    value = tanggalLahir,
                    onValueChange = onTanggalLahirChange,
                    placeholder = { Text("mm/dd/yyyy", color = Color.Gray, fontSize = 14.sp) },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Pilih Tanggal",
                            tint = PrimaryGreen
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = customTextFieldColors(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Nama Orang Tua / Wali
            Column {
                FormLabel(label = "Nama Orang Tua / Wali")
                OutlinedTextField(
                    value = namaOrangTua,
                    onValueChange = onNamaOrangTuaChange,
                    placeholder = { Text("Nama lengkap orang tua", color = Color.Gray, fontSize = 14.sp) },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = customTextFieldColors(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // No Telepon Orang Tua
            Column {
                FormLabel(label = "No Telepon Orang Tua")
                OutlinedTextField(
                    value = noTelpOrangTua,
                    onValueChange = onNoTelpOrangTuaChange,
                    placeholder = { Text("08xx xxxx xxxx", color = Color.Gray, fontSize = 14.sp) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = "Telepon",
                            tint = PrimaryGreen
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = customTextFieldColors(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

// ----------------------------------------------------
// 4. Form Lokasi & Posyandu (Dengan Vertikal Stepper)
// ----------------------------------------------------
@Composable
fun LocationForm(
    kelurahan: String,
    onKelurahanChange: (String) -> Unit,
    dusun: String,
    onDusunChange: (String) -> Unit,
    posyandu: String,
    onPosyanduChange: (String) -> Unit,
    rt: String,
    onRtChange: (String) -> Unit,
    rw: String,
    onRwChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
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
            Text(
                text = "Data Lokasi & Posyandu",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Stepper 1: Pilih Kelurahan
            StepperItem(
                number = "1",
                title = "Pilih Kelurahan",
                isLast = false
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp, bottom = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    val kelurahanOptions = listOf("Ambarketawang", "Balecatur")
                    kelurahanOptions.forEach { option ->
                        val isSelected = option == kelurahan
                        Surface(
                            modifier = Modifier
                                .weight(1f)
                                .height(40.dp)
                                .clickable { onKelurahanChange(option) },
                            shape = RoundedCornerShape(20.dp),
                            color = if (isSelected) PrimaryGreen else LightGreenBg,
                            border = if (isSelected) null else borderStrokeDefault()
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = option,
                                    fontSize = 13.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    color = if (isSelected) Color.White else DarkText
                                )
                            }
                        }
                    }
                }
            }

            // Stepper 2: Cari Dusun
            StepperItem(
                number = "2",
                title = "Cari Dusun",
                isLast = false
            ) {
                OutlinedTextField(
                    value = dusun,
                    onValueChange = onDusunChange,
                    placeholder = { Text("Cari Dusun...", color = Color.Gray, fontSize = 14.sp) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Cari Dusun",
                            tint = PrimaryGreen
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = customTextFieldColors(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp, bottom = 12.dp)
                )
            }

            // Stepper 3: Nama Posyandu + RT & RW
            StepperItem(
                number = "3",
                title = "Nama Posyandu",
                isLast = true
            ) {
                Column(
                    modifier = Modifier.padding(top = 6.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = posyandu,
                        onValueChange = onPosyanduChange,
                        placeholder = { Text("Pilih atau cari Nama Posyandu...", color = Color.Gray, fontSize = 14.sp) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Cari Posyandu",
                                tint = PrimaryGreen
                            )
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        colors = customTextFieldColors(),
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Input RT & RW Berdampingan
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            FormLabel("RT")
                            OutlinedTextField(
                                value = rt,
                                onValueChange = onRtChange,
                                placeholder = { Text("00", color = Color.Gray, fontSize = 14.sp) },
                                singleLine = true,
                                shape = RoundedCornerShape(12.dp),
                                colors = customTextFieldColors(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            FormLabel("RW")
                            OutlinedTextField(
                                value = rw,
                                onValueChange = onRwChange,
                                placeholder = { Text("00", color = Color.Gray, fontSize = 14.sp) },
                                singleLine = true,
                                shape = RoundedCornerShape(12.dp),
                                colors = customTextFieldColors(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    }
}

// Component item Stepper Vertikal
@Composable
fun StepperItem(
    number: String,
    title: String,
    isLast: Boolean,
    content: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        // Kolom Lingkaran & Garis Vertikal
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(32.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(PrimaryGreen),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = number,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .fillMaxHeight()
                        .background(PrimaryGreen.copy(alpha = 0.3f))
                )
            }
        }

        Spacer(modifier = Modifier.width(10.dp))

        // Konten Langkah Stepper
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = if (isLast) 0.dp else 16.dp)
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText
            )
            content()
        }
    }
}

// ----------------------------------------------------
// 5. Footer & Tombol Simpan
// ----------------------------------------------------
@Composable
fun SaveSection(
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "Dengan menekan tombol simpan, Anda menyetujui penyimpanan data kesehatan sesuai kebijakan privasi kami.",
            fontSize = 11.sp,
            color = SoftGrayText,
            textAlign = TextAlign.Center,
            lineHeight = 15.sp,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Button(
            onClick = onSaveClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = "Simpan Data",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "SIMPAN DATA",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    letterSpacing = 0.5.sp
                )
            }
        }
    }
}

// ----------------------------------------------------
// Utility Helpers
// ----------------------------------------------------
@Composable
fun FormLabel(label: String) {
    Text(
        text = label,
        fontSize = 13.sp,
        fontWeight = FontWeight.SemiBold,
        color = DarkText,
        modifier = Modifier.padding(bottom = 6.dp)
    )
}

@Composable
fun customTextFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = PrimaryGreen,
    unfocusedBorderColor = Color(0xFFE2E8F0),
    focusedContainerColor = Color(0xFFFAFAFA),
    unfocusedContainerColor = Color(0xFFFAFAFA)
)

fun borderStrokeDefault() = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFCBD5E1))

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddChildScreenPreview() {
    SiKumbangMadu_KaderTheme {
        AddChildScreen()
    }
}

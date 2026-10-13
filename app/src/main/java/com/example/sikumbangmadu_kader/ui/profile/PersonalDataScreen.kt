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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Domain
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Save
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
private val LightBg = Color(0xFFF4FAF6)
private val DarkText = Color(0xFF1E293B)
private val SoftGrayText = Color(0xFF64748B)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalDataScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {}
) {
    // State Form Data Diri
    var namaLengkap by remember { mutableStateOf("Siti Khadijah") }
    var nomorTelepon by remember { mutableStateOf("081234567890") }
    var email by remember { mutableStateOf("siti.khadijah@email.com") }
    val lokasiPosyandu = remember { "Posyandu Melati - RW 03" } // Read-Only
    var alamatLengkap by remember { mutableStateOf("Jl. Merdeka No. 12, RT 01/RW 02, Desa Sejahtera") }

    Scaffold(
        modifier = modifier,
        topBar = {
            PersonalDataTopBar(onBackClick = onBackClick)
        },
        bottomBar = {
            SaveButtonSection(onSaveClick = onSaveClick)
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
            // 2. Foto Profil
            ProfileImageHeader()

            // 3. Form Data Diri Card
            PersonalDataForm(
                namaLengkap = namaLengkap,
                onNamaLengkapChange = { namaLengkap = it },
                nomorTelepon = nomorTelepon,
                onNomorTeleponChange = { nomorTelepon = it },
                email = email,
                onEmailChange = { email = it },
                lokasiPosyandu = lokasiPosyandu,
                alamatLengkap = alamatLengkap,
                onAlamatLengkapChange = { alamatLengkap = it }
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// ----------------------------------------------------
// 1. TopBar (PersonalDataTopBar)
// ----------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalDataTopBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = "Data Pribadi",
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
// 2. Foto Profil & Ubah Foto Header
// ----------------------------------------------------
@Composable
fun ProfileImageHeader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.BottomEnd) {
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape)
                    .background(PrimaryGreen.copy(alpha = 0.15f))
                    .border(2.dp, PrimaryGreen, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Foto Profil Kader",
                    tint = PrimaryGreen,
                    modifier = Modifier.size(54.dp)
                )
            }

            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(PrimaryGreen)
                    .clickable { /* Action ganti foto */ },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = "Ganti Foto",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Ubah Foto Profil",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = PrimaryGreen,
            modifier = Modifier.clickable { /* Action ganti foto */ }
        )
    }
}

// ----------------------------------------------------
// 3. Form Data Diri (PersonalDataForm)
// ----------------------------------------------------
@Composable
fun PersonalDataForm(
    namaLengkap: String,
    onNamaLengkapChange: (String) -> Unit,
    nomorTelepon: String,
    onNomorTeleponChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    lokasiPosyandu: String,
    alamatLengkap: String,
    onAlamatLengkapChange: (String) -> Unit,
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Informasi Diri & Kontak",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText
            )

            // Nama Lengkap
            Column {
                FormLabel("Nama Lengkap")
                OutlinedTextField(
                    value = namaLengkap,
                    onValueChange = onNamaLengkapChange,
                    placeholder = { Text("Masukkan nama lengkap", color = Color.Gray, fontSize = 14.sp) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Nama",
                            tint = PrimaryGreen
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = customTextFieldColors(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Nomor Telepon
            Column {
                FormLabel("Nomor Telepon")
                OutlinedTextField(
                    value = nomorTelepon,
                    onValueChange = onNomorTeleponChange,
                    placeholder = { Text("08xx xxxx xxxx", color = Color.Gray, fontSize = 14.sp) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = "Nomor Telepon",
                            tint = PrimaryGreen
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = customTextFieldColors(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Email
            Column {
                FormLabel("Email")
                OutlinedTextField(
                    value = email,
                    onValueChange = onEmailChange,
                    placeholder = { Text("contoh@email.com", color = Color.Gray, fontSize = 14.sp) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Email",
                            tint = PrimaryGreen
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = customTextFieldColors(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Lokasi Posyandu Bertugas (Read-Only)
            Column {
                FormLabel("Lokasi Posyandu Bertugas")
                OutlinedTextField(
                    value = lokasiPosyandu,
                    onValueChange = {},
                    readOnly = true,
                    enabled = false,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Domain,
                            contentDescription = "Lokasi Posyandu",
                            tint = SoftGrayText
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        disabledBorderColor = Color(0xFFE2E8F0),
                        disabledContainerColor = Color(0xFFF1F5F9),
                        disabledTextColor = SoftGrayText,
                        disabledLeadingIconColor = SoftGrayText
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "*Ditetapkan oleh Admin Puskesmas",
                    fontSize = 11.sp,
                    color = SoftGrayText,
                    modifier = Modifier.padding(top = 4.dp, start = 2.dp)
                )
            }

            // Alamat Lengkap (Multiline)
            Column {
                FormLabel("Alamat Lengkap")
                OutlinedTextField(
                    value = alamatLengkap,
                    onValueChange = onAlamatLengkapChange,
                    placeholder = { Text("Masukkan alamat lengkap", color = Color.Gray, fontSize = 14.sp) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Alamat",
                            tint = PrimaryGreen
                        )
                    },
                    minLines = 3,
                    maxLines = 3,
                    shape = RoundedCornerShape(12.dp),
                    colors = customTextFieldColors(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

// ----------------------------------------------------
// 4. Tombol Simpan (SaveButtonSection)
// ----------------------------------------------------
@Composable
fun SaveButtonSection(
    onSaveClick: () -> Unit,
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
                        contentDescription = "Simpan Perubahan",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Simpan Perubahan",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
private fun FormLabel(label: String) {
    Text(
        text = label,
        fontSize = 13.sp,
        fontWeight = FontWeight.SemiBold,
        color = DarkText,
        modifier = Modifier.padding(bottom = 6.dp)
    )
}

@Composable
private fun customTextFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = PrimaryGreen,
    unfocusedBorderColor = Color(0xFFE2E8F0),
    focusedContainerColor = Color(0xFFFAFAFA),
    unfocusedContainerColor = Color(0xFFFAFAFA)
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PersonalDataScreenPreview() {
    SiKumbangMadu_KaderTheme {
        PersonalDataScreen()
    }
}

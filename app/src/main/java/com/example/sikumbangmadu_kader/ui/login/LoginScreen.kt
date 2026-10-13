package com.example.sikumbangmadu_kader.ui.login

import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sikumbangmadu_kader.ui.theme.SiKumbangMadu_KaderTheme

// Palet Warna Khas SiKumbangMadu (Posyandu Kader)
val PrimaryGreen = Color(0xFF0D6840)
val LightGreenBackground = Color(0xFFEBF7F1)
val SoftGrayText = Color(0xFF6B7280)
val DarkText = Color(0xFF1F2937)

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginClick: (username: String, password: String) -> Unit = { _, _ -> },
    onForgotPasswordClick: () -> Unit = {}
) {
    // Gradien Halus dari Putih ke Hijau Tosca Muda sesuai spesifikasi
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color.White,
            Color(0xFFF3FAF6),
            LightGreenBackground
        )
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = gradientBrush)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 32.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // 1. Header (Judul Aplikasi & Sub-judul)
            LoginHeader()

            Spacer(modifier = Modifier.height(24.dp))

            // 2. Form Input Card Container
            LoginForm(
                onLoginClick = onLoginClick,
                onForgotPasswordClick = onForgotPasswordClick
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 3. Footer (Ornamen Garis + Hati & Versi)
            LoginFooter()
        }
    }
}

@Composable
fun LoginHeader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Judul Aplikasi
        Text(
            text = "SiKumbangMadu",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryGreen,
            letterSpacing = 0.5.sp
        )

        Text(
            text = "Aplikasi Posyandu Kader",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = PrimaryGreen.copy(alpha = 0.75f),
            modifier = Modifier.padding(top = 2.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Sub-judul
        Text(
            text = "Selamat Datang",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText
        )

        Text(
            text = "Silakan masuk ke akun Anda",
            fontSize = 14.sp,
            color = SoftGrayText,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun LoginForm(
    modifier: Modifier = Modifier,
    onLoginClick: (username: String, password: String) -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            // Input Username
            Text(
                text = "Username",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = DarkText,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                placeholder = { Text("Masukkan username", color = Color.Gray, fontSize = 14.sp) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Icon User",
                        tint = PrimaryGreen
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryGreen,
                    unfocusedBorderColor = Color(0xFFE5E7EB),
                    focusedContainerColor = Color(0xFFFAFAFA),
                    unfocusedContainerColor = Color(0xFFFAFAFA)
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Input Password
            Text(
                text = "Kata Sandi",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = DarkText,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Masukkan kata sandi", color = Color.Gray, fontSize = 14.sp) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Icon Gembok",
                        tint = PrimaryGreen
                    )
                },
                trailingIcon = {
                    val icon = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    val description = if (isPasswordVisible) "Sembunyikan Kata Sandi" else "Tampilkan Kata Sandi"
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(imageVector = icon, contentDescription = description, tint = SoftGrayText)
                    }
                },
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryGreen,
                    unfocusedBorderColor = Color(0xFFE5E7EB),
                    focusedContainerColor = Color(0xFFFAFAFA),
                    unfocusedContainerColor = Color(0xFFFAFAFA)
                ),
                modifier = Modifier.fillMaxWidth()
            )

            // Lupa Kata Sandi (Alignment.End)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                TextButton(onClick = onForgotPasswordClick) {
                    Text(
                        text = "Lupa Kata Sandi?",
                        color = PrimaryGreen,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Tombol Masuk (Lebar penuh, warna hijau tua, icon panah di kanan)
            Button(
                onClick = { onLoginClick(username, password) },
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
                    Text(
                        text = "Masuk",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Icon Masuk",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun LoginFooter(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Ornamen Garis Tipis dengan Hati di Tengah
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = Color(0xFFCBD5E1),
                thickness = 1.dp
            )
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Ornamen Hati",
                tint = PrimaryGreen.copy(alpha = 0.6f),
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .size(16.dp)
            )
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = Color(0xFFCBD5E1),
                thickness = 1.dp
            )
        }

        // Versi Aplikasi
        Text(
            text = "Versi Aplikasi 2.4.0",
            fontSize = 12.sp,
            color = SoftGrayText,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    SiKumbangMadu_KaderTheme {
        LoginScreen()
    }
}

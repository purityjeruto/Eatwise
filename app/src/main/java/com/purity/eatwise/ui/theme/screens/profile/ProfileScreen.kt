package com.purity.eatwise.ui.theme.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.purity.eatwise.R

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Image
        Image(
            painter = painterResource(id = R.drawable.avatar_placeholder),
            contentDescription = "User Avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Name & Email
        Text("Vincent Cheruiyot", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text("vincent@example.com", fontSize = 14.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(24.dp))

        // Health Info Cards
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatCard(title = "Weight", value = "70kg")
            StatCard(title = "Height", value = "175cm")
            StatCard(title = "BMI", value = "22.9")
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Buttons
        Button(
            onClick = { /* TODO: Edit profile */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
        ) {
            Text("Edit Profile", fontWeight = FontWeight.Bold, color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = { /* TODO: Settings */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Settings")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = { /* TODO: Log out */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
        ) {
            Text("Log Out")
        }
    }
}

@Composable
fun StatCard(title: String, value: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF2F2F2)),
        modifier = Modifier
            .width(100.dp)
            .height(80.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(value, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(title, fontSize = 14.sp, color = Color.Gray)
        }
    }
}

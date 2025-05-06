package com.purity.eatwise.ui.theme.screens.healthplan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HealthPlanScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text("Here is your health plan", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        // Daily Nutrition Card
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Daily nutritions", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)

                NutritionProgressBar("Calories", 80, Color.Green)
                NutritionProgressBar("Protein", 35, Color.Cyan)
                NutritionProgressBar("Carbs", 50, Color.Red)
                NutritionProgressBar("Fats", 15, Color.Magenta)
            }
        }

        // Suggested Activities Card
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Suggested activities", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ActivityIcon("Walking", Icons.Default.DirectionsWalk)
                    ActivityIcon("Running", Icons.Default.DirectionsRun)
                    ActivityIcon("Workout", Icons.Default.FitnessCenter)
                    ActivityIcon("Pilates", Icons.Default.SelfImprovement)
                }
            }
        }

        // Get Started Button
        Button(
            onClick = { /* TODO: Implement navigation or logic */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF8C00)),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Get started", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun NutritionProgressBar(label: String, percent: Int, color: Color) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(label)
            Text("$percent%")
        }
        LinearProgressIndicator(
            progress = percent / 100f,
            color = color,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    }
}

@Composable
fun ActivityIcon(label: String, icon: ImageVector) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(icon, contentDescription = label, modifier = Modifier.size(32.dp))
        Text(label, fontSize = 12.sp)
    }
}

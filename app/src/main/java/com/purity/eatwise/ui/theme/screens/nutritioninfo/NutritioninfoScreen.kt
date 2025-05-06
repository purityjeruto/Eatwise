package com.purity.eatwise.ui.theme.screens.nutritioninfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NutritionInformationScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Daily nutritions",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        NutritionProgress("Calories", "2000 kcal", 1.0f, Color.Green)
        NutritionProgress("Protein", "35%", 0.35f, Color.Cyan)
        NutritionProgress("Carbs", "50%", 0.5f, Color.Red)
        NutritionProgress("Fats", "15%", 0.15f, Color.Magenta)
    }
}

@Composable
fun NutritionProgress(name: String, value:String, progress: Float, color: Color) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = name, fontSize = 16.sp)
            Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }
        LinearProgressIndicator(
            progress = progress,
            color = color,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    }
}
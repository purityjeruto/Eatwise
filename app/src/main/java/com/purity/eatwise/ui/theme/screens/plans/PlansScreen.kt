package com.purity.eatwise.ui.theme.screens.plans

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MealPlanningScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Plan Your Meals",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        MealCard("Breakfast", listOf("Oatmeal", "Banana", "Green Tea"))
        MealCard("Lunch", listOf("Grilled Chicken", "Brown Rice", "Salad"))
        MealCard("Dinner", listOf("Baked Fish", "Steamed Veggies"))
        MealCard("Snacks", listOf("Almonds", "Yogurt"))

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { /* TODO: Add navigation or logic */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
        ) {
            Text("Save Meal Plan", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun MealCard(mealType: String, items: List<String>) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(mealType, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(8.dp))
            items.forEach { item ->
                Text("- $item", fontSize = 14.sp)
            }
        }
    }
}

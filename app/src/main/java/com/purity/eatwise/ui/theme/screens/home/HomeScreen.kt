package com.purity.eatwise.ui.theme.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        // Top Header
        Text("Caloric friend", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        // Calories Summary
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            CalorieStat("200 kcal", "eaten")
            CalorieStat("1800 kcal", "goal")
            CalorieStat("80 kcal", "burned")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Macronutrients Bars
        NutrientBar("Protein", 40f, 120f, Color.Green)
        NutrientBar("Carbs", 150f, 200f, Color.Yellow)
        NutrientBar("Fats", 13.5f, 90f, Color.Blue)

        Spacer(modifier = Modifier.height(16.dp))

        // Meal List
        MealItem("Breakfast", "450-550 kcal")
        MealItem("Snack", "180 kcal")
        MealItem("Lunch", "650-750 kcal")
        MealItem("Snack", "100 kcal")
        MealItem("Dinner", "400 kcal")

        Spacer(modifier = Modifier.height(16.dp))

        // Bottom Navigation (Simplified)
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Text("Home")
            Text("Add")
            Text("Recipes")
            Text("Plans")
        }
    }
}

@Composable
fun CalorieStat(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(label, fontSize = 14.sp, textAlign = TextAlign.Center)
    }
}

@Composable
fun NutrientBar(name: String, current: Float, total: Float, color: Color) {
    Column {
        Text("$name: ${current.toInt()}g / ${total.toInt()}g")
        LinearProgressIndicator(
            progress = current / total,
            color = color,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun MealItem(meal: String, calories: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(meal)
            Text(calories)
            Icon(Icons.Default.Add, contentDescription = "Add")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}

package com.purity.eatwise.ui.theme.screens.nutritiontracker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.purity.eatwise.ui.theme.EatWiseTheme
import com.purity.eatwise.ui.theme.newNavy
import com.purity.eatwise.ui.theme.newOrange

@Composable
fun NutritionTrackerScreen(navController: NavController) {
    var foodInput by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf(0) }
    val maxCalories = 2000

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(newNavy)
    ) {
        Text(
            text = "Nutrition Tracker",
            color = newOrange,
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = foodInput,
            onValueChange = { foodInput = it },
            label = { Text("What did you eat?", color = newOrange) },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (foodInput.isNotBlank()) {
                    calories += 150 // Mock calculation - in real app you'd calculate based on food
                    foodInput = "" // Clear input after adding
                }
            },
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()


        ) {
            Text("Add Food")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Today's Calories: $calories / $maxCalories kcal",
            style = MaterialTheme.typography.bodyLarge,
            color = newOrange,
        )

        LinearProgressIndicator(
            progress = { calories.toFloat() / maxCalories },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .background(newNavy),
            color = when {
                calories > maxCalories -> Color.Red
                calories > maxCalories * 0.8 -> Color.Yellow
                else -> Color(0xFF4CAF50)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TrackerPreview() {
    EatWiseTheme { // Your app's theme
        NutritionTrackerScreen(
            navController = rememberNavController()
        )
    }
}

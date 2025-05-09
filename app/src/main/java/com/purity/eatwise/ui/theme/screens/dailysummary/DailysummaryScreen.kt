package com.purity.eatwise.ui.theme.screens.dailysummary


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import com.purity.eatwise.data.FoodItem
import com.purity.eatwise.viewmodel.CalorieViewModel
import com.purity.eatwise.viewmodel.FoodItem

//import com.example.calorietracker.models.FoodItem
//import com.example.calorietracker.viewmodels.CalorieViewModel

@Composable
fun DailySummaryScreen(viewModel: CalorieViewModel) {
    val userProfile by viewModel.userProfile.collectAsState()
    val todayFoods by viewModel.todayFoods.collectAsState()
    val remaining = viewModel.getRemainingCalories()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Your Food Today", style = MaterialTheme.typography.headlineMedium)

        LinearProgressIndicator(
            progress = { (userProfile.dailyCalorieGoal - remaining).toFloat() / userProfile.dailyCalorieGoal },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Goal: ${userProfile.dailyCalorieGoal} kcal")
        Text("Eaten: ${viewModel.calculateDailyCalories()} kcal")
        Text("Remaining: $remaining kcal")

        Spacer(modifier = Modifier.height(16.dp))

        if (todayFoods.isEmpty()) {
            Text("No foods added today")
        } else {
            LazyColumn {
                items(todayFoods) { food ->
                    FoodItemRow(food = food)
                }
            }
        }
    }
}

@Composable
fun FoodItemRow(food: FoodItem) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(food.name)
            Text("${food.calories} kcal")
        }
    }
}

@Composable
fun DailySummaryScreen(
    viewModel: CalorieViewModel,
    onBack: () -> Unit
) {
    Column {
        Button(onClick = onBack) { Text("Back") }
        // ... (rest of existing code)
    }
}
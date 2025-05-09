package com.purity.eatwise.ui.theme.screens.addfood



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.purity.eatwise.model.Fooditem
import com.purity.eatwise.viewmodel.CalorieViewModel

//import com.purity.eatwise.models.FoodItem
//import com.purity.eatwise.viewmodels.CalorieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFoodScreen(viewModel: CalorieViewModel) {
    var foodName by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("What Did You Eat?", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = foodName,
            onValueChange = { foodName = it },
            label = { Text("Food Name") }
        )

        OutlinedTextField(
            value = calories,
            onValueChange = { calories = it },
            label = { Text("Calories") }
        )

        Button(
            onClick = {
                val calorieValue = calories.toIntOrNull() ?: 0
                viewModel.addFood(Fooditem(foodName, calorieValue))
                foodName = ""
                calories = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Food")
        }
    }
}

private fun ColumnScope.Fooditem(
    string: String,
    i: Int
): Fooditem {
    TODO("Not yet implemented")
}

@Composable
fun AddFoodScreen(
    viewModel: CalorieViewModel,
    onBack: () -> Unit,
    onNext: () -> Unit
) {
    // ... (existing code)

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Button(onClick = onBack) { Text("Back") }
        Button(onClick = onNext) { Text("See Summary") }
    }
}
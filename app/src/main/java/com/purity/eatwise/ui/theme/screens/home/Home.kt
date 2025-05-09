package com.purity.eatwise.ui.theme.screens.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.purity.eatwise.ui.theme.newOrange

//import androidx.compose.ui.text.input.KeyboardOptions

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen() {
    // 🧺 This is the live-updating list of food
    val foodList = remember { mutableStateListOf(
        Food("Apple", 95),
        Food("Banana", 105)
    )}

    // 🍞 User input for new food
    var newFoodName by remember { mutableStateOf("") }
    var newFoodCalories by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(newOrange)
    ) {
        Text("🍽 My Calories", fontSize = 28.sp, modifier = Modifier.padding(bottom = 16.dp))

        // 📝 Input Fields
        OutlinedTextField(
            value = newFoodName,
            onValueChange = { newFoodName = it },
            label = { Text("Food name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = newFoodCalories,
            onValueChange = { newFoodCalories = it },
            label = { Text("Calories") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // ➕ Add Button
        Button(
            onClick = {
                val calories = newFoodCalories.toIntOrNull()
                if (newFoodName.isNotBlank() && calories != null) {
                    foodList.add(Food(newFoodName.trim(), calories))
                    newFoodName = ""
                    newFoodCalories = ""
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Add Food")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 📋 Food List
        LazyColumn {
            items(foodList) { food ->
                FoodItem(food, calorieValue)
            }
        }
    }
}

@Composable
fun FoodItem(food: String, calorieValue: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = food.name, fontSize = 20.sp)
            Text(text = "${food.calories} kcal", fontSize = 18.sp)
        }
    }
}

// 📦 The food data class
data class Food(val name: String, val calories: Int)

@Preview
@Composable
private fun Homeprev() {
    HomeScreen()
    
}
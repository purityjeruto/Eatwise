package com.purity.eatwise.ui.theme.screens.mealselection

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.purity.eatwise.ui.theme.newNavy
import com.purity.sokomart.model.Product
import com.purity.sokomart.ui.viewmodel.ProductViewModel

@Composable
fun MealSelectionScreen(
    navController: NavController,
    viewModel: ProductViewModel = viewModel()
) {
    var selectedMealType by remember { mutableStateOf("Breakfast") }

    LaunchedEffect(selectedMealType) {
        viewModel.loadMeals(selectedMealType)
    }

    val meals by viewModel.meals.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(newNavy)
    ) {
        Text("Select a Meal", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))

        // Meal type selector
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            listOf("Breakfast", "Lunch", "Dinner", "Snack").forEach { type ->
                Button(
                    onClick = { selectedMealType = type },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    modifier = Modifier.padding(horizontal = 4.dp)
                ) {
                    Text(type, color = Color.Black)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // List of meals
        meals.forEach { meal ->
            MealOptionItem(meal) {
                println("Meal selected: ${meal.name}")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Text("Home", color = Color.White)
            Text("Add", color = Color.White)
            Text("Recipes", color = Color.White)
            Text("Plans", color = Color.White)
        }
    }
}

@Composable
fun MealOptionItem(meal: Product, onSelect: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onSelect() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(meal.name)
            Text("${meal.calories} kcal")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MealSelectionPreview() {
    MealSelectionScreen(navController = rememberNavController())
}

package com.purity.eatwise.ui.theme.screens.mealplanner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.purity.eatwise.ui.theme.EatWiseTheme
import com.purity.eatwise.ui.theme.newNavy
import com.purity.eatwise.ui.theme.newOrange
import com.purity.eatwise.ui.theme.screens.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealPlannerScreen(navController: NavController) {
    var selectedMeal by remember { mutableStateOf("Breakfast") }
    var isExpanded by remember { mutableStateOf(false) } // State for dropdown expansion
    val meals = listOf("Breakfast", "Lunch", "Dinner")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(newNavy)
    ) {
        Text("Meal Planner", style = MaterialTheme.typography.headlineLarge, color = newOrange)
        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown menu for meal selection
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it }
        ) {
            TextField(
                value = selectedMeal,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor() // This modifier is important for the menu to work
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                meals.forEach { meal ->
                    DropdownMenuItem(
                        text = { Text(meal) },
                        onClick = {
                            selectedMeal = meal
                            isExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text("Suggested Meal:", color = newOrange, style = MaterialTheme.typography.headlineLarge)
        Text(
            when (selectedMeal) {
                "Breakfast" -> "Oatmeal + Fruits (300 kcal)"
                "Lunch" -> "Grilled Chicken + Veggies (450 kcal)"
                else -> "Salmon + Quinoa (500 kcal)"
            }

        )
    }
}
@Preview(showBackground = true)
@Composable
fun MealPlannerPreview() {
    EatWiseTheme { // Your app's theme
        MealPlannerScreen(
            navController = rememberNavController()
        )
    }
}

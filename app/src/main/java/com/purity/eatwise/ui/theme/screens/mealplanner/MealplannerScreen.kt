package com.purity.eatwise.ui.theme.screens.mealplanner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.purity.eatwise.navigation.ROUT_HOME
import com.purity.eatwise.navigation.ROUT_MEAL
import com.purity.eatwise.navigation.ROUT_NUTRITION
import com.purity.eatwise.navigation.ROUT_RECIPES
import com.purity.eatwise.ui.theme.EatWiseTheme
import com.purity.eatwise.ui.theme.newNavy
import com.purity.eatwise.ui.theme.newOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealPlannerScreen(navController: NavController) {
    var selectedMeal by remember { mutableStateOf("Breakfast") }
    var isExpanded by remember { mutableStateOf(false) }
    val meals = listOf("Breakfast", "Lunch", "Dinner")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Meal Planning", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFA4911))
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color(0xFFEC3D07)) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = false,
                    onClick = { navController.navigate(ROUT_HOME) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Star, contentDescription = "Recipe") },
                    label = { Text("Recipe") },
                    selected = true,
                    onClick = { navController.navigate(ROUT_RECIPES) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Track") },
                    label = { Text("Track") },
                    selected = false,
                    onClick = { navController.navigate(ROUT_NUTRITION) }
                )
            }
        },
        containerColor = Color(0xFF38AD11)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
                .background(newNavy)
        ) {
            Text("Meal Planner", style = MaterialTheme.typography.headlineLarge, color = newOrange)
            Spacer(modifier = Modifier.height(16.dp))

            // Dropdown menu
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
                        .menuAnchor()
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

            // Meal Suggestion Section
            Text("Suggested Meal:", color = newOrange, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(12.dp))

            when (selectedMeal) {
                "Breakfast" -> {
                    MealSuggestionCard(
                        mealTitle = "Oatmeal & Fruits",
                        mealDescription = "Warm oatmeal topped with bananas, berries, and honey (300 kcal).",
                        backgroundColor = Color(0xFFEF6C00)
                    )
                }
                "Lunch" -> {
                    MealSuggestionCard(
                        mealTitle = "Grilled Chicken & Veggies",
                        mealDescription = "Grilled chicken breast with mixed vegetables and brown rice (450 kcal).",
                        backgroundColor = Color(0xFF43A047)
                    )
                }
                else -> {
                    MealSuggestionCard(
                        mealTitle = "Salmon & Quinoa",
                        mealDescription = "Pan-seared salmon with quinoa and steamed broccoli (500 kcal).",
                        backgroundColor = Color(0xFF1E88E5)
                    )
                }
            }
        }
    }
}

@Composable
fun MealSuggestionCard(
    mealTitle: String,
    mealDescription: String,
    backgroundColor: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = mealTitle,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = mealDescription,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MealPlannerPreview() {
    EatWiseTheme {
        MealPlannerScreen(navController = rememberNavController())
    }
}

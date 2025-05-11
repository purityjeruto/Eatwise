package com.purity.eatwise.ui.theme.screens.quickrecipes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.purity.eatwise.ui.theme.EatWiseTheme
import com.purity.eatwise.ui.theme.Pink40
import com.purity.eatwise.ui.theme.newNavy
import com.purity.eatwise.ui.theme.newOrange
import com.purity.eatwise.ui.theme.screens.mealplanner.MealPlannerScreen


@Composable
fun QuickRecipesScreen(navController: NavController) {
    val recipes = listOf(
        "Green Smoothie" to "Spinach, Banana, Almond Milk",
        "Grilled Chicken Salad" to "Chicken, Lettuce, Tomatoes, Olive Oil",
        "Quinoa Bowl" to "Quinoa, Avocado, Chickpeas, Lemon"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(newNavy)
    ) {
        Text("Quick Recipes", color = newOrange, style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        recipes.forEach { (name, ingredients) ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Pink40)
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(name, color = newOrange, style = MaterialTheme.typography.headlineLarge)
                    Text(ingredients, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun RecipesPreview() {
    EatWiseTheme { // Your app's theme
        QuickRecipesScreen(
            navController = rememberNavController()
        )
    }
}

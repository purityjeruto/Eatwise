package com.purity.eatwise.ui.theme.screens.quickrecipes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.purity.eatwise.navigation.ROUT_ADD_PRODUCT
import com.purity.eatwise.navigation.ROUT_HOME
import com.purity.eatwise.navigation.ROUT_MEAL
import com.purity.eatwise.navigation.ROUT_NUTRITION
import com.purity.eatwise.navigation.ROUT_TIPS
import com.purity.eatwise.ui.theme.EatWiseTheme
import com.purity.eatwise.ui.theme.Pink40
import com.purity.eatwise.ui.theme.newNavy
import com.purity.eatwise.ui.theme.newOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuickRecipesScreen(navController: NavController) {
    val recipes = listOf(
        "Green Smoothie" to "Spinach, Banana, Almond Milk",
        "Grilled Chicken Salad" to "Chicken, Lettuce, Tomatoes, Olive Oil",
        "Quinoa Bowl" to "Quinoa, Avocado, Chickpeas, Lemon",
        "Oatmeal Delight" to "Oats, Berries, Honey, Almonds",
        "Veggie Stir-fry" to "Broccoli, Carrots, Bell Peppers, Soy Sauce"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Write your Recipe", color = Color.White) },
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
                    icon = { Icon(Icons.Default.Add, contentDescription = "add") },
                    label = { Text("add") },
                    selected = true,
                    onClick = { navController.navigate(ROUT_ADD_PRODUCT) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Notifications, contentDescription = "Reminder") },
                    label = { Text("Reminder") },
                    selected = false,
                    onClick = { navController.navigate(ROUT_TIPS) }
                )
            }
        },
        containerColor = Color(0xFF38AD11)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(newNavy)
        ) {
            // Tip Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Stay Healthy!",
                        style = MaterialTheme.typography.titleMedium,
                        color = newNavy
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Quick meals can still be nutritious. Choose whole foods and keep it colorful!",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.DarkGray
                    )
                }
            }

            // Recipes Title
            Text("Quick Recipes", color = newOrange, style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(12.dp))

            // Recipe Cards
            recipes.forEach { (name, ingredients) ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Pink40),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(name, color = newOrange, style = MaterialTheme.typography.titleLarge)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(ingredients, style = MaterialTheme.typography.bodyLarge, color = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Tip of the Day Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Tip of the Day",
                        style = MaterialTheme.typography.titleMedium,
                        color = newNavy
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Prep ingredients in advance to make weekday cooking quicker and stress-free.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipesPreview() {
    EatWiseTheme {
        QuickRecipesScreen(
            navController = rememberNavController()
        )
    }
}

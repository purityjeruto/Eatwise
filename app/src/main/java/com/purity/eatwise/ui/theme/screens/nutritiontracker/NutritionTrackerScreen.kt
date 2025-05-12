// Required imports for Card and related components
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.purity.eatwise.navigation.ROUT_HOME
import com.purity.eatwise.navigation.ROUT_MEAL
import com.purity.eatwise.navigation.ROUT_NUTRITION
import com.purity.eatwise.ui.theme.EatWiseTheme
import com.purity.eatwise.ui.theme.newNavy
import com.purity.eatwise.ui.theme.newOrange
import com.purity.eatwise.ui.theme.screens.mealplanner.MealPlannerScreen

// ... existing imports

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun NutritionTrackerScreen(navController: NavController) {
    var foodInput by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf(0) }
    val maxCalories = 2000

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nutrition Tracking", color = Color.White) },
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
                    icon = { Icon(Icons.Default.Star, contentDescription = "plan") },
                    label = { Text("plan") },
                    selected = true,
                    onClick = { navController.navigate(ROUT_MEAL) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "track") },
                    label = { Text("track") },
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
                        calories = 150 // Mock calculation
                        foodInput = ""
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
                    .padding(top = 8.dp),
                color = when {
                    calories > maxCalories -> Color.Red
                    calories > maxCalories * 0.8 -> Color.Yellow
                    else -> Color(0xFF4CAF50)
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // New content section: Info Cards
            Text(
                text = "Quick Tips",
                color = newOrange,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                InfoCard(title = "Hydration", content = "Drink at least 8 glasses of water daily.")
                InfoCard(title = "Meal Ideas", content = "Try a quinoa salad with grilled chicken for a balanced meal.")
                InfoCard(title = "Nutrition Tip", content = "Include more fiber by eating vegetables and whole grains.")
                InfoCard(title = "Smart Snacking", content = "Opt for nuts, yogurt, or fruit instead of chips.")
            }
        }
    }
}

@Composable
fun InfoCard(title: String, content: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 80.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = newNavy
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = content,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun NutritionPreview() {
    EatWiseTheme {
        NutritionTrackerScreen(navController = rememberNavController())
    }
}
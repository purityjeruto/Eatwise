package com.purity.eatwise.ui.theme.screens.tips

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.purity.eatwise.navigation.ROUT_RECIPES
import com.purity.eatwise.ui.theme.EatWiseTheme
import com.purity.eatwise.ui.theme.newNavy
import com.purity.eatwise.ui.theme.newOrange
import com.purity.eatwise.ui.theme.newWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipsScreen(navController: NavController) {
    var notificationsEnabled by remember { mutableStateOf(false) }
    val tips = listOf(
        "Drink water before meals!",
        "Eat slowly to avoid overeating.",
        "Include protein in every meal."
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tips & Reminders", color = Color.White) },
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
                    icon = { Icon(Icons.Default.Notifications, contentDescription = "Track") },
                    label = { Text("Track") },
                    selected = true,
                    onClick = { navController.navigate(ROUT_NUTRITION) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Recipe") },
                    label = { Text("Recipe") },
                    selected = false,
                    onClick = { navController.navigate(ROUT_RECIPES) }
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
                text = "Health Tips",
                color = newOrange,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(16.dp))

            tips.forEach { tip ->
                Text(
                    text = "• $tip",
                    color = newWhite,
                    modifier = Modifier.padding(vertical = 4.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Enable Daily Reminders",
                    color = newOrange,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )

                Switch(
                    checked = notificationsEnabled,
                    onCheckedChange = { notificationsEnabled = it }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Additional cards added below
            InfoCard(title = "Hydration Tip", content = "Start your day with a glass of water to jumpstart hydration.")
            Spacer(modifier = Modifier.height(12.dp))

            InfoCard(title = "Motivational Quote", content = "\"Take care of your body. It’s the only place you have to live.\" – Jim Rohn")
            Spacer(modifier = Modifier.height(12.dp))

            InfoCard(title = "Sleep Reminder", content = "Try to get 7–9 hours of sleep each night to support overall health.")
            Spacer(modifier = Modifier.height(12.dp))

            InfoCard(title = "Mindful Eating", content = "Avoid distractions during meals to improve digestion and satisfaction.")
        }
    }
}

@Composable
fun InfoCard(title: String, content: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleMedium, color = newNavy)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = content, style = MaterialTheme.typography.bodyMedium, color = Color.DarkGray)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TipsPreview() {
    EatWiseTheme {
        TipsScreen(
            navController = rememberNavController()
        )
    }
}


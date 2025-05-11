package com.purity.eatwise.ui.theme.screens.tips

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.purity.eatwise.ui.theme.EatWiseTheme
import com.purity.eatwise.ui.theme.newNavy
import com.purity.eatwise.ui.theme.newOrange
import com.purity.eatwise.ui.theme.newWhite
import com.purity.eatwise.ui.theme.screens.mealplanner.MealPlannerScreen

@Composable
fun TipsScreen(navController: NavController) {
    var notificationsEnabled by remember { mutableStateOf(false) }
    val tips = listOf(
        "Drink water before meals!",
        "Eat slowly to avoid overeating.",
        "Include protein in every meal."
    )

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
            ) // This parenthesis was missing

            Switch(
                checked = notificationsEnabled,
                onCheckedChange = { notificationsEnabled = it }
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun TipsPreview() {
    EatWiseTheme { // Your app's theme
        TipsScreen(
            navController = rememberNavController()
        )
    }
}

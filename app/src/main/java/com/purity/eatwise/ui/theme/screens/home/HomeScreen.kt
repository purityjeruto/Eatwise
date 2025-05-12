package com.purity.eatwise.ui.theme.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.purity.eatwise.R
import com.purity.eatwise.navigation.ROUT_MEAL
import com.purity.eatwise.ui.theme.EatWiseTheme
import com.purity.eatwise.ui.theme.newBlack
import com.purity.eatwise.ui.theme.newNavy
import com.purity.eatwise.ui.theme.newOrange

@Composable
fun HomeScreen (navController: NavController,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(newNavy), // Green theme
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.home), // Make sure this image exists
            contentDescription = "EatWise Logo",
            tint = Color.White,
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "EatWise",
            style = MaterialTheme.typography.headlineLarge, // Updated for Material3
            color = newOrange
        )
        Text(
            text = "Smart Choices, One Bite at a Time!",
            style = MaterialTheme.typography.bodyLarge, // Updated for Material3
            color = newOrange
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
           onClick = {
               navController.navigate(ROUT_MEAL)
           },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color(0xFF4CAF50)
            ),
            modifier = Modifier.padding(16.dp).background(newOrange)
        ) {
            Text("Get Started", color = newBlack)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    EatWiseTheme { // Your app's theme
        HomeScreen(
            navController = rememberNavController()

        )
    }
}